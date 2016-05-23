/*
 * 
 */
package recall.model.automata;

import java.io.Serializable;
import java.util.Set;
import recall.model.actions.RelativizedAction;

/**
 *
 * @author wellington
 */
public class Transition implements Serializable{
    
    private static int TRANSITION_CONT = 1;
    
    private State from;
    private State to;
    private Set<RelativizedAction> actions;
    private int id;

    public Transition(State from, State to, Set<RelativizedAction> actions) {
        this.from = from;
        this.to = to;
        this.actions = actions;
        this.id = TRANSITION_CONT++;
    }
    
    //<editor-fold defaultstate="collapsed" desc="properties">
    
    public Set<RelativizedAction> getActions() {
        return actions;
    }
    
    public void setActions(Set<RelativizedAction> actions) {
        this.actions = actions;
    }
    
    public State getFrom() {
        return from;
    }
    
    public void setFrom(State from) {
        this.from = from;
    }
    
    public State getTo() {
        return to;
    }
    
    public void setTo(State to) {
        this.to = to;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    //</editor-fold>

    public int getId() {
        return id;
    }
  
}
