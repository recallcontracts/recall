/*
 * 
 */
package recall.util;

import java.util.ArrayList;
import java.util.Optional;
import recall.model.automata.Automaton;
import recall.model.automata.StateSituation;
import recall.model.automata.Transition;

/**
 *
 * @author wellington
 */
public class AutomatonExporter {

    public static String dumpStates(Automaton automaton) {
        StringBuilder sb = new StringBuilder("id;clause;situation\n");
        automaton.getStates().stream().sorted().forEach(s -> sb.append(String.format("%d;%s;%s\n", s.getId(), s.getClause(), s.getSituation())));
        //sb.append("\n");
        return sb.toString();
    }

    public static String dumpToDot(Automaton automaton) {
        StringBuilder sb = new StringBuilder("digraph contract {\nrankdir=LR;\n");
        sb.append("node [shape = point, color=white, fontcolor=white]; start;");
        automaton.getStates().stream().filter(s -> s.getSituation()==StateSituation.notChecked || s.getSituation()==StateSituation.conflictFree).forEach(
                s -> sb.append(String.format("node [shape = circle, color=black, fontcolor=black, tooltip=\"%s\"]; S%d ;", s.getClause(), s.getId())));
        sb.append("\n");
        automaton.getStates().stream().filter(s -> s.getSituation()==StateSituation.violating).forEach(
                s -> sb.append(String.format("node [shape = circle, color=red, fontcolor=white, style=filled, fillcolor=red, tooltip=\"%s\"]; S%d ",
                                s.getClause(), s.getId())));
        sb.append("\n");
        automaton.getStates().stream().filter(s -> s.getSituation()==StateSituation.satisfaction).forEach(
                s -> sb.append(String.format("node [shape = circle, color=green, fontcolor=white, style=filled, fillcolor=green, tooltip=\"%s\"]; S%d ",
                                s.getClause(), s.getId())));
        sb.append("\n");
        automaton.getStates().stream().filter(s -> s.getSituation()==StateSituation.conflicting).forEach(
                s -> sb.append(String.format("node [shape = circle, color=orange, fontcolor=white, style=filled, fillcolor=orange, tooltip=\"%s\"]; S%d ",
                                s.getClause(), s.getId())));

        sb.append(String.format("start -> S%d\n", automaton.getInitial().getId()));
        automaton.getTransitions().forEach(t
                -> sb.append(String.format("\tS%d -> S%d [ label = \"%s\" ];\n",
                                t.getFrom().getId(), t.getTo().getId(), t.getActions()))
        );
        sb.append("}\n");
        return sb.toString();
    }

    public static String dumpToMinDot(Automaton automaton) {
        StringBuilder sb = new StringBuilder("digraph contract {\nrankdir=LR;\n");
        sb.append("node [shape = point, color=white, fontcolor=white]; start;");
        automaton.getStates().stream().filter(s -> s.getSituation()==StateSituation.notChecked || s.getSituation()==StateSituation.conflictFree).forEach(
                s -> sb.append(String.format("node [shape = circle, color=black, fontcolor=black, tooltip=\"%s\"]; S%d ;", s.getClause(), s.getId())));
        sb.append("\n");
        automaton.getStates().stream().filter(s -> s.getSituation()==StateSituation.violating).forEach(
                s -> sb.append(String.format("node [shape = circle, color=red, fontcolor=white, style=filled, fillcolor=red, tooltip=\"%s\"]; S%d ",
                                s.getClause(), s.getId())));
        sb.append("\n");
        automaton.getStates().stream().filter(s -> s.getSituation()==StateSituation.satisfaction).forEach(
                s -> sb.append(String.format("node [shape = circle, color=green, fontcolor=white, style=filled, fillcolor=green, tooltip=\"%s\"]; S%d ",
                                s.getClause(), s.getId())));
        sb.append("\n");
        automaton.getStates().stream().filter(s -> s.getSituation()==StateSituation.conflicting).forEach(
                s -> sb.append(String.format("node [shape = circle, color=orange, fontcolor=white, style=filled, fillcolor=orange, tooltip=\"%s\"]; S%d ",
                                s.getClause(), s.getId())));
        
        sb.append(String.format("start -> S%d\n", automaton.getInitial().getId()));

        ArrayList<Transition> ls = new ArrayList<>();

        automaton.getTransitions().forEach(t -> {
            Optional<Transition> e = ls.stream().filter(tt -> tt.getFrom().equals(t.getFrom()) && tt.getTo().equals(t.getTo())).findFirst();
            if (e.isPresent()) {
                Transition g = e.get();
                if (g.getActions().size() < t.getActions().size()) {
                    g.setActions(t.getActions());
                }
            } else {
                ls.add(new Transition(t.getFrom(), t.getTo(), t.getActions()));
            }

        });

        ls.forEach(t
                -> sb.append(String.format("\tS%d -> S%d [ label = \"%s\" ];\n",
                                t.getFrom().getId(), t.getTo().getId(), t.getActions()))
        );
        sb.append("}\n");
        return sb.toString();
    }
}
