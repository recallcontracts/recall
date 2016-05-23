/*
 * 
 */
package recall.algorithms;

import com.google.common.collect.Sets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import recall.model.actions.BasicAction;
import recall.model.actions.RelativizedAction;
import recall.model.contracts.BooleanClause;
import recall.model.contracts.Clause;
import recall.model.contracts.Conflict;
import recall.util.ContractUtil;
import recall.util.LogLevel;
import recall.util.LogType;
import recall.util.Logger;

/**
 *
 * @author wellington
 */
public class ActionExtractor {

    private final Set<Integer> individuals;
    private final List<Conflict> conflicts;
    private final Map<Clause, List<Set<RelativizedAction>>> calculated;

    public ActionExtractor(Set<Integer> individuals, List<Conflict> conflicts) {
        this.individuals = individuals;
        this.conflicts = conflicts;
        calculated = new HashMap<>();
    }

    public List<Set<RelativizedAction>> calculateConcurrentRelativizedActions(Clause clause) {
        Clause c = ClauseDecomposer.processComposedActions((Clause) clause.clone());
        if (calculated.containsKey(c)) {
            return calculated.get(c);
        }
        HashSet<RelativizedAction> actions = calculateRelativizedActions(c);
        if (Logger.getInstance().getLevel() == LogLevel.VERBOSE) {
            Logger.getInstance().log(LogType.ADDITIONAL, "Concurrent Relativized Actions for " + c + " is " + actions);
        }
        List<Set<RelativizedAction>> list = ContractUtil.calculateConcurrentRelativizedActions(actions, conflicts);
        if (actions.size() == 1) {
            list.add(Sets.newHashSet(RelativizedAction.negation(actions.iterator().next())));
        }
        calculated.put(c, list);
        return list;
    }

    public HashSet<RelativizedAction> calculateRelativizedActions(Clause c) {
        HashSet<RelativizedAction> actions = new HashSet<>();
        if (c instanceof BooleanClause) {
            return actions;
        }
        switch (c.getRelativizationType()) {
            case DIRECTED:
                c.getAction().getBasicActions().forEach(a
                        -> actions.add(new RelativizedAction(c.getSender(), (BasicAction) a, c.getReceiver()))
                );
                break;
            case RELATIVIZED:
                individuals.forEach(j -> {
                    if (!(Objects.equals(c.getSender(), j) && individuals.size() > 1)) {
                        c.getAction().getBasicActions().forEach(a
                                -> actions.add(new RelativizedAction(c.getSender(), (BasicAction) a, j))
                        );
                    }
                });
                break;
            case GLOBAL:
                individuals.forEach(i -> {
                    individuals.forEach(j -> {
                        if (!(Objects.equals(i, j) && individuals.size() > 1)) {
                            c.getAction().getBasicActions().forEach(a
                                    -> actions.add(new RelativizedAction(i, (BasicAction) a, j))
                            );
                        }
                    });
                });
                break;
        }
        if (c.getComposition() != null && c.getComposition().getOther() != null) {
            actions.addAll(calculateRelativizedActions(c.getComposition().getOther()));
        }
        return actions;
    }
}
