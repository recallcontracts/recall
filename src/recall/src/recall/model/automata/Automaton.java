/*
 * 
 */
package recall.model.automata;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import recall.model.contracts.Clause;
import recall.model.contracts.Contract;

/**
 *
 * @author wellington
 */
public class Automaton implements Serializable {

    private Contract contract;
    private Set<State> states;
    private State initial;
    private Set<Transition> transitions;

    private boolean conflictFound;

    public Automaton(Contract contract) {
        this.contract = contract;
        this.states = new HashSet<>();
        this.initial = new State(contract.getFullContract());
        if (this.initial.getClause() != null) {
            this.states.add(this.initial);
        }
        this.transitions = new HashSet<>();
        conflictFound = false;
    }

    //<editor-fold defaultstate="collapsed" desc="properties">
    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }

    public State getInitial() {
        return initial;
    }

    public void setInitial(State initial) {
        this.initial = initial;
    }

    public Set<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(Set<Transition> transitions) {
        this.transitions = transitions;
    }

    public boolean isConflictFound() {
        return conflictFound;
    }

    public void setConflictFound(boolean conflictFound) {
        this.conflictFound = conflictFound;
    }

    public List<State> getConflicts() {
        return states.stream().filter(s -> s.getSituation() == StateSituation.conflicting)
                .collect(Collectors.toList());
    }
    //</editor-fold>

    public State getStateByClause(Clause c) {
        State state = null;
        Stream<State> filter = states.stream().filter(s -> s.getClause().equals(c));
        Optional<State> found = filter.findFirst();
        state = found.isPresent() ? found.get() : null;
        return state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("States: %d \n", states.size()));
        sb.append(String.format("Transitions: %d \n", transitions.size()));
        sb.append(String.format("Conflicting: %b \n", conflictFound));
        return sb.toString();
    }

}
