package recall.algorithms;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import recall.model.actions.BasicAction;
import recall.model.actions.ComposedAction;
import recall.model.automata.ConflictInformation;
import recall.model.automata.DeonticTag;
import recall.model.automata.State;
import recall.model.automata.StateSituation;
import recall.model.contracts.Clause;
import recall.model.contracts.ClauseCompositionType;
import recall.model.contracts.Conflict;
import recall.model.contracts.ConflictType;
import recall.model.contracts.DeonticClause;
import recall.model.contracts.DeonticClauseType;
import recall.model.contracts.RelativizationType;

/**
 *
 * @author wellington
 */
public class ConflictSearcher {

    private final Set<Integer> individuals;
    private final List<Conflict> conflicts;

    public ConflictSearcher(Set<Integer> individuals, List<Conflict> conflicts) {
        this.individuals = individuals;
        this.conflicts = conflicts;
    }

    public boolean hasConflict(State state) {
        Clause clause = state.getClause();
        clause = ClauseDecomposer.processComposedActions(clause);
        Set<Set<DeonticTag>> delta = extractTags(clause);
        for (Set<DeonticTag> d1 : delta) {
            for (Set<DeonticTag> d2 : Sets.difference(delta, Sets.newHashSet(d1))) {
                for (DeonticTag d : d1) {
                    Set<DeonticTag> conflictSet = generateConflictSet(d);
                    Sets.SetView<DeonticTag> intersection = Sets.intersection(conflictSet, d2);
                    if (!intersection.isEmpty()) {
                        state.setSituation(StateSituation.conflicting);
                        state.setConflictInformation(new ConflictInformation(d, intersection, d2));
                        return true;
                    }
                }
            }
        }
        state.setSituation(StateSituation.conflictFree);
        return false;
    }

    //<editor-fold defaultstate="collapsed" desc="auxiliary methods">
    /**
     * Delta function that returns all deontic tags from a given clause
     *
     * @param clause
     * @return deontic tags
     */
    private Set<Set<DeonticTag>> extractTags(Clause clause) {
        Set<Set<DeonticTag>> list = new HashSet<>();
        //dynamic and boolean clauses
        if (clause instanceof DeonticClause) {
            DeonticClause c = (DeonticClause) clause.clone();
            if (c.getAction() instanceof ComposedAction) {
                c = (DeonticClause) ClauseDecomposer.processDeonticComposedActions(c);
            }
            final Set<DeonticTag> dt = new HashSet<>();
            final DeonticClause dc = c;
            switch (clause.getRelativizationType()) {
                case GLOBAL:
                    dc.getAction().getBasicActions().forEach(a
                            -> dt.add(new DeonticTag(dc.getDeonticType(), (BasicAction) a))
                    );
                    break;
                case RELATIVIZED:
                    dc.getAction().getBasicActions().forEach(a
                            -> dt.add(new DeonticTag(dc.getDeonticType(), (BasicAction) a, dc.getSender()))
                    );
                    break;
                case DIRECTED:
                    dc.getAction().getBasicActions().forEach(a
                            -> dt.add(new DeonticTag(dc.getDeonticType(), (BasicAction) a, dc.getSender(), dc.getReceiver()))
                    );
                    break;
            }
            list.add(dt);
        }
        if (clause.getComposition() != null) {
            if (clause.getComposition().getType() == ClauseCompositionType.AND) {
                list = Sets.union(list, extractTags(clause.getComposition().getOther()));
            } else {
                Set<Set<DeonticTag>> otherList = extractTags(clause.getComposition().getOther());
                list.addAll(otherList);
            }
        }
        return list;
    }

    /**
     * F# function that returns all conflicting deontic tags from a given tag
     *
     * @param tag
     * @return all possible conflicts for tag
     */
    private Set<DeonticTag> generateConflictSet(DeonticTag tag) {
        Set<DeonticTag> list = new HashSet<>();
        switch (tag.getType()) {
            case OBLIGATION:
                list.addAll(generateTagsByType(DeonticClauseType.PROHIBITION, tag));
                list.addAll(getPredefinedConflicts(tag, DeonticClauseType.OBLIGATION, DeonticClauseType.PERMISSION));
                break;
            case PERMISSION:
                list.addAll(generateTagsByType(DeonticClauseType.PROHIBITION, tag));
                list.addAll(getPredefinedConflicts(tag, DeonticClauseType.OBLIGATION));
                break;
            case PROHIBITION:
                list.addAll(generateTagsByType(DeonticClauseType.OBLIGATION, tag));
                list.addAll(generateTagsByType(DeonticClauseType.PERMISSION, tag));
                break;
        }
        return list;
    }

    /**
     * This auxiliary method returns all predefined conflicts in conflict list
     * for a determined action/tag
     *
     * @param tag containing the action to search for predefined conflicts
     * @param types of conflicts to generate if exists a determined conflict
     * @return all predefined conflicts
     */
    private Set<DeonticTag> getPredefinedConflicts(DeonticTag tag, DeonticClauseType... types) {
        Set<DeonticTag> list = new HashSet<>();
        conflicts.stream().forEach(c -> {
            if (c.getA().equals(tag.getAction())) {
                if (c.getType() == ConflictType.GLOBAL) {
                    for (DeonticClauseType t : types) {
                        list.addAll(generateTagsByType(t, new DeonticTag(t, c.getB())));
                    }
                } else {
                    for (DeonticClauseType t : types) {
                        if (tag.getRelativization() == RelativizationType.GLOBAL) {
                            list.addAll(generateTagsByType(t, new DeonticTag(t, c.getB())));
                        } else {
                            list.addAll(generateRelativizedTags(t, c.getB(), tag.getSender()));
                        }
                    }
                }
            }
        });
        return list;
    }

    /**
     * Generates tags for a given type
     *
     * @param type of tag
     * @param action included in tags
     * @param i is the sender of all tags
     * @return A set of tags
     */
    private Set<DeonticTag> generateRelativizedTags(DeonticClauseType type, BasicAction action, Integer i) {
        Set<DeonticTag> list = new HashSet<>();
        list.add(new DeonticTag(type, action)); //GLOBAL
        list.add(new DeonticTag(type, action, i)); //RELATIVIZED
        individuals.stream().forEach(j -> {
            list.add(new DeonticTag(type, action, i, j)); //DIRECTED
        });
        return list;
    }

    /**
     * This method generates all kind of tags by a given type
     *
     * @param deonticClauseType the type of tags
     * @param tag the additional information about the generated tags like
     * action, sender and receiver. In future can be changed by a
     * RelativizedAction
     * @return A set of tags with all possible types
     */
    private Set<DeonticTag> generateTagsByType(DeonticClauseType deonticClauseType, DeonticTag tag) {
        Set<DeonticTag> list = new HashSet<>();
        switch (tag.getRelativization()) {
            case GLOBAL: {
                list.add(new DeonticTag(deonticClauseType, tag.getAction())); //GLOBAL
                individuals.stream().forEach(i -> {
                    list.add(new DeonticTag(deonticClauseType, tag.getAction(), i)); //RELATIVIZED
                    individuals.stream().forEach(j -> {
                        list.add(new DeonticTag(deonticClauseType, tag.getAction(), i, j)); //DIRECTED
                    });
                });
                break;
            }
            case RELATIVIZED: {
                Integer i = tag.getSender();
                list.add(new DeonticTag(deonticClauseType, tag.getAction())); //GLOBAL
                list.add(new DeonticTag(deonticClauseType, tag.getAction(), i)); //RELATIVIZED
                individuals.stream().forEach(j -> {
                    list.add(new DeonticTag(deonticClauseType, tag.getAction(), i, j)); //DIRECTED
                });
                break;
            }
            case DIRECTED: {
                list.add(new DeonticTag(deonticClauseType, tag.getAction())); //GLOBAL
                Integer i = tag.getSender();
                Integer j = tag.getReceiver();
                //list.add(new DeonticTag(deonticClauseType, tag.getAction(), i)); //RELATIVIZED
                list.add(new DeonticTag(deonticClauseType, tag.getAction(), i, j)); //DIRECTED
                break;
            }
        }
        return list;
    }
//</editor-fold>

}
