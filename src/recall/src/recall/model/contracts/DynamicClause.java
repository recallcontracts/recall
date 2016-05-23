/*
 * 
 */
package recall.model.contracts;

import java.io.Serializable;
import java.util.Objects;
import recall.model.actions.Action;

/**
 *
 * @author wellington
 */
public class DynamicClause extends Clause implements Serializable {

    private Clause clause;

    public DynamicClause() {
        super(null);
    }

    public DynamicClause(int sender, int receiver, Action action, Clause clause) {
        super(sender, receiver, action);
        this.clause = clause;
    }

    public DynamicClause(int sender, Action action, Clause clause) {
        super(sender, action);
        this.clause = clause;
    }

    public DynamicClause(Action action, Clause clause) {
        super(action);
        this.clause = clause;
    }

    //<editor-fold defaultstate="collapsed" desc="properties">
    public Clause getClause() {
        return clause;
    }

    public void setClause(Clause clause) {
        this.clause = clause;
    }
//</editor-fold>

    @Override
    public String toString() {
        if (getValue() == null) {
            String cmp = getComposition() != null ? getComposition().toString() : "";
            return String.format("%s[%s](%s)%s", formatRelativization(), getAction(), getClause(), cmp);
        } else if (getValue()) {
            return "T";
        } else {
            return "F";
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.clause);
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
        final DynamicClause other = (DynamicClause) obj;
        if (!Objects.equals(this.clause, other.clause)) {
            return false;
        }
        return super.equalsBase(obj);
    }

    @Override
    public Object clone() {
        DynamicClause dynamicClause = new DynamicClause(this.getSender(), this.getReceiver(), this.getAction(), this.getClause());
        if (getComposition() != null) {
            dynamicClause.setComposition((ClauseComposition) getComposition().clone());
        }
        return dynamicClause;
    }

}
