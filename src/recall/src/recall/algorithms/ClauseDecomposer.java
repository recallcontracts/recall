/*
 * 
 */
package recall.algorithms;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import recall.model.actions.Action;
import recall.model.actions.ActionOperator;
import recall.model.actions.BasicAction;
import recall.model.actions.ComposedAction;
import recall.model.actions.RelativizedAction;
import recall.model.contracts.BooleanClause;
import recall.model.contracts.Clause;
import recall.model.contracts.ClauseComposition;
import recall.model.contracts.ClauseCompositionType;
import recall.model.contracts.DeonticClause;
import recall.model.contracts.DynamicClause;
import recall.model.contracts.RelativizationType;

/**
 *
 * @author wellington
 */
public class ClauseDecomposer {

    private final Set<Integer> individuals;
    private final boolean ignoreSelfActions;

    public ClauseDecomposer(Set<Integer> individuals, boolean ignoreSelfActions) {
        this.individuals = individuals;
        this.ignoreSelfActions = ignoreSelfActions;
    }

    /**
     * Decomposes a clause considering the set of actions
     *
     * @param c the clause that be decomposed
     * @param actions the set of actions to decompose c
     * @return the resulting decomposed clause. Can be a BooleanClause, if the
     * clause can be fully decomposed, or a residual clause obtained by the
     * execution of the set of actions in the trace.
     */
    public Clause decompose(Clause c, Set<RelativizedAction> actions) {
        if (c.getComposition() != null) {
            Clause cc = (Clause) c.clone();
            cc.setComposition(null);
            Clause c1 = decomposeSingle(cc, actions);

            Clause c2 = decompose(c.getComposition().getOther(), actions);

            return combine(c1, c2, c.getComposition().getType());
        } else {
            return decomposeSingle(c, actions);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="decompositions">
    /**
     * Decompose a single clause without ClauseCompositions
     *
     * @param c the clause that be decomposed
     * @param actions the set of actions to decompose c
     * @return the resulting decomposed clause.
     */
    private Clause decomposeSingle(Clause c, Set<RelativizedAction> actions) {
        if (c instanceof BooleanClause) {
            return c;
        }
        if (c.getAction() instanceof ComposedAction) {
            return decompose(processComposedActions(c), actions);
        }
        if (c instanceof DeonticClause) {
            return decomposeDeontic((DeonticClause) c, actions);
        }
        if (c instanceof DynamicClause) {
            return decomposeDynamic((DynamicClause) c, actions);
        }
        return c;
    }

    /**
     *
     * @param c
     * @param actions
     * @return
     */
    private Clause decomposeDynamic(DynamicClause c, Set<RelativizedAction> actions) {
        BasicAction a = (BasicAction) c.getAction();
        if (a.isSkip()) {
            return c.getClause();
        }
        Set<RelativizedAction> clauseActions = generateRelativizedActions(c);
        boolean sat = false;
        switch (c.getRelativizationType()) {
            case RELATIVIZED:
                sat = !Sets.intersection(clauseActions, actions).isEmpty();
                break;
            case GLOBAL:
            case DIRECTED:
                sat = actions.containsAll(clauseActions);
                break;
        }
        if (a.isNegation()) {
            sat = !sat;
        }
        if (sat) {
            return c.getClause();
        } else {
            return BooleanClause.TRUE;
        }
    }

    /**
     *
     * @param c
     * @param actions
     * @return
     */
    private Clause decomposeDeontic(DeonticClause c, Set<RelativizedAction> actions) {
        BasicAction a = (BasicAction) c.getAction();

        if (a.isViolation() || a.isSkip()) {
            return decomposeDeonticSpecial(c, actions);
        }

        Set<RelativizedAction> clauseActions = generateRelativizedActions(c);
        switch (c.getDeonticType()) {
            case OBLIGATION: {
                if (c.getRelativizationType() == RelativizationType.RELATIVIZED) {
                    if (!Sets.intersection(actions, clauseActions).isEmpty()) {
                        return BooleanClause.TRUE;
                    }
                    return c.getPenalty();
                } else if (actions.containsAll(clauseActions)) { //GLOBAL / DIRECTED
                    return BooleanClause.TRUE;
                }
                return c.getPenalty();
            }
            case PROHIBITION: {
                if (Sets.intersection(actions, clauseActions).isEmpty()) {
                    return BooleanClause.TRUE;
                } else {
                    return c.getPenalty();
                }
            }
            case PERMISSION:
            default: {
                return BooleanClause.TRUE;
            }
        }
    }

    /**
     * Combines two clauses in a composed clause.
     *
     * @param c1 first clause, used as head
     * @param c2 the other clause used in ClauseComposition
     * @param type type of composition
     * @return the first clause with a composition with the other clause.
     */
    private Clause combine(Clause c1, Clause c2, ClauseCompositionType type) {
        if (c1 instanceof BooleanClause && c2 instanceof BooleanClause) {
            return evaluateBoolean((BooleanClause) c1, (BooleanClause) c2, type);
        } else if (c1 instanceof BooleanClause) {
            return combineClause(c2, (BooleanClause) c1, type);
        } else if (c2 instanceof BooleanClause) {
            return combineClause(c1, (BooleanClause) c2, type);
        } else {
            if (c2.contains(type, c1)) {
                return c2;
            }
            Clause c = (Clause) c1.clone();
            c.setComposition(new ClauseComposition(type, (Clause) c2.clone()));
            return c;
        }
    }

    /**
     * Evaluate two boolean clauses returning the boolean result obtained with
     * one of the logic operators
     *
     * @param c1 the first clause
     * @param c2 the second clause
     * @param type the operator type
     * @return a boolean result based on the given logic operator
     */
    private Clause evaluateBoolean(BooleanClause c1, BooleanClause c2, ClauseCompositionType type) {
        switch (type) {
            case AND:
                return new BooleanClause(c1.getValue() && c2.getValue());
            case OR:
                return new BooleanClause(c1.getValue() || c2.getValue());
            case XOR:
                return new BooleanClause((c1.getValue() && !c2.getValue()) || (!c1.getValue() && c2.getValue()));
        }
        return new BooleanClause(false);
    }

    /**
     * Combines two clauses in a composed clause with a boolean evaluation if is
     * the case.
     *
     * @param c1 first clause, used as head
     * @param c2 the boolean clause used in ClauseComposition
     * @param type type of composition
     * @return the first clause with a composition with the other clause if
     * can't be resolved.
     */
    private Clause combineClause(Clause c1, BooleanClause c2, ClauseCompositionType type) {
        switch (type) {
            case AND:
                if (c2.getValue()) {
                    return c1;
                } else {
                    return c2;
                }
            case OR:
            case XOR:
                Clause c = (Clause) c1.clone();
                c.setComposition(new ClauseComposition(type, c2));
                return c;
        }
        return (Clause) c1.clone();
    }

    /**
     * Generates a set with all relativized actions that matches with the given
     * clause
     *
     * @param c is a clause used to generate the actions
     * @return a set with relativized actions
     */
    private Set<RelativizedAction> generateRelativizedActions(Clause c) {
        boolean ignore = (individuals.size() > 1) ? ignoreSelfActions : false;
        Set<RelativizedAction> set = new HashSet<>();

        final List<Action> acts = c.getAction().getBasicActions();

        switch (c.getRelativizationType()) {
            case DIRECTED: {
                acts.forEach((Action a)
                        -> set.add(new RelativizedAction(c.getSender(), (BasicAction) a, c.getReceiver()))
                );
                break;
            }
            case RELATIVIZED: {
                individuals.forEach(j -> {
                    if (!(ignore && Objects.equals(c.getSender(), j))) {
                        acts.forEach((Action a)
                                -> set.add(new RelativizedAction(c.getSender(), (BasicAction) a, j))
                        );
                    }
                });
                break;
            }
            case GLOBAL: {
                individuals.forEach(i
                        -> individuals.forEach(j
                                -> {
                            if (!(ignore && i.equals(j))) {
                                acts.forEach((Action a)
                                        -> set.add(new RelativizedAction(i, (BasicAction) a, j))
                                );
                            }
                        }
                        )
                );
                break;
            }
        }
        return set;
    }

    private Clause decomposeDeonticSpecial(DeonticClause c, Set<RelativizedAction> actions) {
        if (!(c.getAction() instanceof BasicAction)) {
            return c;
        }
        final BasicAction a = (BasicAction) c.getAction();
        switch (c.getDeonticType()) {
            case OBLIGATION: {
                if (a.isSkip()) {
                    return BooleanClause.TRUE;
                } else {
                    return c.getPenalty();
                }
            }
            case PROHIBITION: {
                if (a.isViolation()) {
                    return BooleanClause.TRUE;
                } else {
                    return c.getPenalty();
                }
            }
            case PERMISSION:
            default: {
                return BooleanClause.TRUE;
            }
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="predefined decompositions">
    public static Clause processComposedActions(Clause c) {
        if (c.getComposition() != null) {
            Clause cc = (Clause) c.clone();
            cc.setComposition(null);
            Clause c1 = processSingleComposedActions(cc);

            Clause c2 = processComposedActions(c.getComposition().getOther());

            Clause cf = (Clause) c1.clone();
            cf.getTail().setComposition(new ClauseComposition(c.getComposition().getType(), (Clause) c2.clone()));
            return cf;
        } else {
            return processSingleComposedActions(c);
        }
    }

    public static Clause processSingleComposedActions(Clause c) {
        if (c instanceof BooleanClause || (c.getAction() instanceof BasicAction)) {
            return c;
        }
        if (c instanceof DeonticClause) {
            return processComposedActions(processDeonticComposedActions((DeonticClause) c));
        } else {
            return processComposedActions(processDynamicComposedActions((DynamicClause) c));
        }
    }

    public static Clause processDeonticComposedActions(DeonticClause c) {
        if (!(c.getAction() instanceof ComposedAction)) {
            return c;
        }
        switch (c.getDeonticType()) {
            case OBLIGATION:
                return processComposedObligation(c);
            case PROHIBITION:
                return processComposedProhibition(c);
            case PERMISSION:
                return processComposedPermission(c);
        }
        return c;
    }

    private static Clause processComposedObligation(DeonticClause c) {
        ComposedAction a = (ComposedAction) c.getAction().clone();
        switch (a.getOperator()) {
            case CONCURRENCY: {
                Clause c1 = (Clause) c.clone();
                Clause c2 = (Clause) c.clone();
                c1.setAction((Action) a.getLeft().clone());
                c2.setAction((Action) a.getRight().clone());
                c1.setComposition(new ClauseComposition(ClauseCompositionType.AND, c2));
                return c1;
            }
            case SEQUENCE: {
                Clause c1 = (Clause) c.clone();
                Clause c2 = (Clause) c.clone();
                c1.setAction((Action) a.getLeft().clone());
                c2.setAction((Action) a.getRight().clone());
                c2.setComposition(null);
                Clause cd = new DynamicClause(c1.getSender(), c1.getReceiver(), (Action) a.getLeft().clone(), c2);
                cd.setComposition(c1.getComposition());
                c1.setComposition(new ClauseComposition(ClauseCompositionType.AND, cd));
                return c1;
            }
            case CHOICE: {
                Clause ca1 = (Clause) c.clone();
                Clause cb1 = (Clause) c.clone();
                ca1.setAction((Action) a.getLeft().clone());
                cb1.setAction((Action) a.getRight().clone());
                Clause ca = (Clause) ca1.clone();
                Clause cb = (Clause) cb1.clone();
//                ca1.setComposition(new ClauseComposition(ClauseCompositionType.AND, cb1));
//                cb1.setComposition(new ClauseComposition(ClauseCompositionType.XOR, ca));
//                ca.setComposition(new ClauseComposition(ClauseCompositionType.XOR, cb));
                ca1.setComposition(new ClauseComposition(ClauseCompositionType.OR, cb1));
                return ca1;
            }
        }
        return c;
    }

    private static Clause processComposedProhibition(DeonticClause c) {
        ComposedAction a = (ComposedAction) c.getAction().clone();
        switch (a.getOperator()) {
            case CHOICE:
            case CONCURRENCY: {
                Clause c1 = (Clause) c.clone();
                Clause c2 = (Clause) c.clone();
                c1.setAction((Action) a.getLeft().clone());
                c2.setAction((Action) a.getRight().clone());
                c1.setComposition(new ClauseComposition(ClauseCompositionType.AND, c2));
                return c1;
            }
            case SEQUENCE: {
                Clause c1 = (Clause) c.clone();
                Clause c2 = (Clause) c.clone();
                c1.setAction((Action) a.getLeft().clone());
                c2.setAction((Action) a.getRight().clone());
                c2.setComposition(null);
                Clause cd = new DynamicClause(c1.getSender(), c1.getReceiver(), (Action) a.getLeft().clone(), c2);
                cd.setComposition(c1.getComposition());
                c1.setComposition(new ClauseComposition(ClauseCompositionType.OR, cd));
                return c1;
            }
        }
        return c;
    }

    private static Clause processComposedPermission(DeonticClause c) {
        ComposedAction a = (ComposedAction) c.getAction().clone();
        switch (a.getOperator()) {
            case CHOICE:
            case CONCURRENCY: {
                Clause c1 = (Clause) c.clone();
                Clause c2 = (Clause) c.clone();
                c1.setAction((Action) a.getLeft().clone());
                c2.setAction((Action) a.getRight().clone());
                c1.setComposition(new ClauseComposition(ClauseCompositionType.AND, c2));
                return c1;
            }
            case SEQUENCE: {
                Clause c1 = (Clause) c.clone();
                Clause c2 = (Clause) c.clone();
                c1.setAction((Action) a.getLeft().clone());
                c2.setAction((Action) a.getRight().clone());
                c2.setComposition(null);
                Clause cd = new DynamicClause(c1.getSender(), c1.getReceiver(), (Action) a.getLeft().clone(), c2);
                cd.setComposition(c1.getComposition());
                c1.setComposition(new ClauseComposition(ClauseCompositionType.AND, cd));
                return c1;
            }
        }
        return c;
    }

    public static Clause processDynamicComposedActions(DynamicClause c) {
        if (!(c.getAction() instanceof ComposedAction)) {
            return c;
        }

        ComposedAction act = (ComposedAction) c.getAction().clone();
        switch (act.getOperator()) {
            case CONCURRENCY: {
                List<Action> conc = act.getBasicActions();
                Clause c1 = (Clause) c.clone();
                BasicAction head = null;
                if (conc.size() > 0) {
                    Action get = conc.get(0);
                    if (get instanceof BasicAction) {
                        head = (BasicAction) get;
                        for (Action a : conc) {
                            head.getConcurrentActions().add((BasicAction) a);
                        }
                    }
                }
                c1.setAction(head);
                return c1;
            }
            case CHOICE: {
                Clause c1 = (Clause) c.clone();
                Clause c2 = (Clause) c.clone();
                c1.setAction(act.getLeft());
                c2.setAction(act.getRight());
                c1.setComposition(new ClauseComposition(ClauseCompositionType.AND, c2));
                return c1;
            }
            case SEQUENCE: {
                Clause c1 = (Clause) c.clone();
                c1.setAction(act.getRight());
                Clause cd = new DynamicClause(c1.getSender(), c1.getReceiver(), act.getLeft(), c1);
                cd.setComposition(c1.getComposition());
                c1.setComposition(null);
                return cd;
            }
            case NEGATION: {
                return processNegationComposedActions(c);
            }
            case STAR: {
                Clause clause1 = (Clause) c.clone(); //[a*]C
                Clause clause2 = (Clause) c.getClause().clone();//C
                Action a = act.getLeft();
                clause2.setComposition(c.getComposition());//the other compositions
                DynamicClause dc = new DynamicClause(c.getSender(), c.getReceiver(), a, clause1); //[a]([a*]C)
                clause2.setComposition(new ClauseComposition(ClauseCompositionType.AND, dc));//[a]([a*]C) ^ C
                return clause2;
            }
        }
        return c;
    }

    private static Clause processNegationComposedActions(DynamicClause c) {
        if (c.getAction() == null) {
            return BooleanClause.FALSE;
        }
        ComposedAction act = (ComposedAction) c.getAction().clone();
        if (act.getLeft() == null) {
            return BooleanClause.FALSE;
        }
        Action a = act.getLeft();
        if (a instanceof BasicAction) {
            BasicAction aa = (BasicAction) a;
            Clause c1 = (Clause) c.clone();
            aa.setNegation(true);
            c1.setAction(aa);
            return c1;
        }

        ComposedAction aa = (ComposedAction) a;
        switch (aa.getOperator()) {
            case SEQUENCE: {
                Clause c1 = (Clause) c.clone();
                c1.setAction(new ComposedAction(aa.getRight(), null, ActionOperator.NEGATION));
                Clause cd = new DynamicClause(c1.getSender(), c1.getReceiver(), new ComposedAction(aa.getLeft(), null, ActionOperator.NEGATION), c1);
                cd.setComposition(c1.getComposition());
                c1.setComposition(null);
                return cd;
            }
            case CONCURRENCY: {
                Clause c1 = (Clause) c.clone();
                Clause c2 = (Clause) c.clone();
                c1.setAction(new ComposedAction(aa.getLeft(), null, ActionOperator.NEGATION));
                c2.setAction(new ComposedAction(aa.getRight(), null, ActionOperator.NEGATION));
                c1.setComposition(new ClauseComposition(ClauseCompositionType.AND, c2));
                return c1;
            }
            case CHOICE: {
                Clause c1 = (Clause) c.clone();
                Clause c2 = (Clause) c.clone();
                c1.setAction(new ComposedAction(aa.getLeft(), null, ActionOperator.NEGATION));
                c2.setAction(new ComposedAction(aa.getRight(), null, ActionOperator.NEGATION));
                c1.setComposition(new ClauseComposition(ClauseCompositionType.OR, c2));
                return c1;
            }
            case STAR: {
                Clause c1 = (Clause) c.clone();
                Action left = new ComposedAction(aa.getLeft(), null, ActionOperator.NEGATION);

                c1.setAction(new ComposedAction(left, null, ActionOperator.STAR));
                return c1;
            }
        }
        return c;
    }

//</editor-fold>
}
