/*
 * 
 */
package recall.util;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import recall.model.actions.Action;
import recall.model.actions.BasicAction;
import recall.model.actions.RelativizedAction;
import recall.model.contracts.Clause;
import recall.model.contracts.Conflict;
import recall.model.contracts.ConflictType;
import recall.model.contracts.DeonticClause;
import recall.model.contracts.DynamicClause;
import static recall.model.contracts.RelativizationType.DIRECTED;
import static recall.model.contracts.RelativizationType.GLOBAL;

/**
 *
 * @author wellington
 */
public class ContractUtil {

    //<editor-fold defaultstate="collapsed" desc="auxiliary methods for relativized actions">
    public static Set<RelativizedAction> calculateRelativizedActions(Set<BasicAction> actions, Set<Integer> individuals, boolean ignoreSelfActions) {
        boolean ignore = (individuals.size() > 1) ? ignoreSelfActions : false;

        Set<RelativizedAction> relativizedActions = new HashSet<>();
        individuals.forEach(i -> {
            actions.forEach(a -> {
                individuals.forEach(j -> {
                    if (!(ignore && i.equals(j))) {
                        relativizedActions.add(new RelativizedAction(i, a, j));
                    }
                });
            });
        });
        return relativizedActions;
    }

    public static List<Set<RelativizedAction>> conflictPruning(Set<Set<RelativizedAction>> actions, List<Conflict> conflicts) {
        List<Set<RelativizedAction>> list = new ArrayList<>(actions.size());
        for (Set<RelativizedAction> a : actions) {
            if (isValid(a, conflicts)) {
                list.add(a);
            }
        }
        return list;
    }

    public static void reverse(List<Set<RelativizedAction>> actions) {
        Comparator<Set<RelativizedAction>> comp = new Comparator<Set<RelativizedAction>>() {

            @Override
            public int compare(Set<RelativizedAction> o1, Set<RelativizedAction> o2) {
                return Integer.compare(o1.size(), o2.size());
            }
        };
        actions.sort(comp);
        Collections.reverse(actions);
    }

    public static boolean isValid(Set<RelativizedAction> actions, List<Conflict> conflicts) {
        if (actions.isEmpty()) {
            return false;
        }
        for (Conflict c : conflicts) {
            Set<RelativizedAction> actionsA = actions.stream().filter(
                    i -> i.getAction().equals(c.getA())
            ).collect(Collectors.toSet());
            Set<RelativizedAction> actionsB = actions.stream().filter(
                    i -> i.getAction().equals(c.getB())
            ).collect(Collectors.toSet());
            long countA = actionsA.size();
            long countB = actionsB.size();
            if (c.getType() == ConflictType.GLOBAL && countA > 0 && countB > 0) {
                return false;
            }

            boolean anyMatch = actionsA.stream().anyMatch(i -> actionsB.stream().anyMatch(j -> j.getSender() == i.getSender()));
            if (c.getType() == ConflictType.RELATIVIZED && anyMatch) {
                return false;
            }
        }
        return true;
    }

    public static List<Set<RelativizedAction>> calculateConcurrentRelativizedActions(Set<RelativizedAction> relativizedActions, List<Conflict> conflicts) {
        List<Set<RelativizedAction>> concurrentRelativizedActions;
        long currentTimeMillisIn = System.currentTimeMillis();

        try {
            Set<Set<RelativizedAction>> ps = Sets.powerSet(relativizedActions);
            concurrentRelativizedActions = conflictPruning(ps, conflicts);
            reverse(concurrentRelativizedActions);
        } catch (Exception e) {
            Logger.getInstance().log(LogType.MINIMAL, "Error on calculate Concurrent Relativized Actions: " + e.getMessage());
            return new ArrayList<>(Arrays.asList(relativizedActions));
        }
        Logger.getInstance().log(LogType.ADDITIONAL, String.format("calculated %d concurrent relativized actions in %d ms", concurrentRelativizedActions.size(), System.currentTimeMillis() - currentTimeMillisIn));
        return concurrentRelativizedActions;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="extraction methods">
    public static void extractItems(Clause c, Set<Integer> individuals, Set<Action> actions) {
        if (c == null) {
            return;
        }
        switch (c.getRelativizationType()) {
            case GLOBAL:
                break;
            case RELATIVIZED:
                individuals.add(c.getSender());
                break;
            case DIRECTED:
                individuals.add(c.getSender());
                individuals.add(c.getReceiver());
                break;
        }

        if (c.getComposition() != null) {
            extractItems(c.getComposition().getOther(), individuals, actions);
        }
        if (c instanceof DeonticClause) {
            DeonticClause cc = (DeonticClause) c;
            extractActions(cc.getAction(), actions);
            extractItems(cc.getPenalty(), individuals, actions);
        } else if (c instanceof DynamicClause) {
            DynamicClause cc = (DynamicClause) c;
            extractActions(cc.getAction(), actions);
            extractItems(cc.getClause(), individuals, actions);
        }
    }

    public static void extractActions(Action action, Set<Action> actions) {
        if (action == null) {
            return;
        }
        if (!actions.contains(action)) {
            actions.add(action);
        }

//        if (action.getComposition() != null) {
//            extractActions(action.getComposition().getOther(), actions);
//        }
    }
//</editor-fold>


}
