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
public class DeonticClause extends Clause implements Serializable {

    private DeonticClauseType deonticType;
    private Clause penalty;

    public DeonticClause(int sender, int receiver, Action action, DeonticClauseType type, Clause penalty) {
        super(sender, receiver, action);
        this.deonticType = type;
        this.penalty = penalty;
    }

    public DeonticClause(int sender, Action action, DeonticClauseType type, Clause penalty) {
        super(sender, action);
        this.deonticType = type;
        this.penalty = penalty;
    }

    public DeonticClause(Action action, DeonticClauseType type, Clause penalty) {
        super(action);
        this.deonticType = type;
        this.penalty = penalty;
    }

    //<editor-fold defaultstate="collapsed" desc="properties">
    public Clause getPenalty() {
        if (penalty == null)//categorical
        {
            return BooleanClause.FALSE;
        }
        return penalty;
    }

    public void setPenalty(Clause penalty) {
        this.penalty = penalty;
    }

    public DeonticClauseType getDeonticType() {
        return deonticType;
    }

    public void setDeonticType(DeonticClauseType deonticType) {
        this.deonticType = deonticType;
    }

//</editor-fold>
    @Override
    public String toString() {
        if (getValue() == null) {
            String cmp = getComposition() != null ? getComposition().toString() : "";
            return String.format("%s%s(%s)%s%s", formatRelativization(), getDeonticType(), getAction(), formatPenalty(), cmp);
        } else if (getValue()) {
            return "T";
        } else {
            return "F";
        }
    }

    private String formatPenalty() {
        if (penalty != null) {
            return "_/" + penalty + "/_";
        }
        return "";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.deonticType);
        hash = 37 * hash + Objects.hashCode(this.penalty);
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
        final DeonticClause other = (DeonticClause) obj;
        if (this.deonticType != other.deonticType) {
            return false;
        }
        if (!Objects.equals(this.penalty, other.penalty)) {
            return false;
        }
        return super.equalsBase(obj);
    }

    @Override
    public Object clone() {
        DeonticClause deonticClause = new DeonticClause(this.getSender(), this.getReceiver(), this.getAction(), this.getDeonticType(), this.getPenalty());
        if (getComposition() != null) {
            deonticClause.setComposition((ClauseComposition) getComposition().clone());
        }
        return deonticClause;
    }

}
