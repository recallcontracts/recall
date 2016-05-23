/*
 * 
 */
package recall.model.automata;

import java.io.Serializable;
import java.util.Stack;
import recall.model.contracts.Clause;

/**
 *
 * @author wellington
 */
public class State implements Comparable<State>, Serializable {

    private static int STATE_CONT = 0;

    private int id;
    private Clause clause;
    private StateSituation situation;
    private ConflictInformation conflictInformation;
    private Stack<Transition> trace;

    public State(int id, Clause clause) {
        this.id = id;
        this.clause = clause;
        this.situation = StateSituation.notChecked;
        this.trace = new Stack<>();
        if (id > STATE_CONT) {
            STATE_CONT = id;
        }
    }

    public State(Clause clause) {
        this(STATE_CONT++, clause);
    }

    @Override
    public String toString() {
        return String.format("(%d) %s", id, clause);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    //<editor-fold defaultstate="collapsed" desc="properties">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clause getClause() {
        return clause;
    }

    public void setClause(Clause clause) {
        this.clause = clause;
    }

    public StateSituation getSituation() {
        return situation;
    }

    public void setSituation(StateSituation situation) {
        this.situation = situation;
    }

    public ConflictInformation getConflictInformation() {
        return conflictInformation;
    }

    public void setConflictInformation(ConflictInformation conflictInformation) {
        this.conflictInformation = conflictInformation;
    }

    public Stack<Transition> getTrace() {
        return trace;
    }

    //</editor-fold>
    
    @Override
    public int compareTo(State o) {
        return Integer.compare(id, o.getId());
    }

}
