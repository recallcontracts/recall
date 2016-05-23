/*
 * 
 */
package recall.model.contracts;

import java.io.Serializable;
import java.util.Objects;
import recall.model.actions.Action;
import recall.parser.Symbol;
import recall.parser.SymbolTable;

/**
 *
 * @author wellington
 */
public abstract class Clause implements Cloneable, Serializable {

    private Integer sender;
    private Integer receiver;
    private RelativizationType relativizationType;
    private Boolean value;
    private ClauseComposition composition;
    private Action action;

    public Clause(Integer sender, Integer receiver, RelativizationType type, Boolean value, ClauseComposition composition, Action action) {
        this.sender = sender;
        this.receiver = receiver;
        this.relativizationType = type;
        this.value = value;
        this.composition = composition;
        this.action = action;
    }

    public Clause(Integer sender, int receiver, Action action) {
        this.sender = sender;
        this.receiver = receiver;
        if (sender < 0) {
            this.relativizationType = RelativizationType.GLOBAL;
        } else if (receiver < 0) {
            this.relativizationType = RelativizationType.RELATIVIZED;
        } else {
            this.relativizationType = RelativizationType.DIRECTED;
        }
        this.value = null;
        this.composition = null;
        this.action = action;
    }

    public Clause(Integer sender, Action action) {
        this.sender = sender;
        this.receiver = -1;
        this.relativizationType = RelativizationType.RELATIVIZED;
        this.value = null;
        this.composition = null;
        this.action = action;
    }

    public Clause(Action action) {
        this.sender = -1;
        this.receiver = -1;
        this.relativizationType = RelativizationType.GLOBAL;
        this.value = null;
        this.composition = null;
        this.action = action;
    }

    public String formatIndividual(int id) {
        Symbol i = SymbolTable.getInstance().getSymbolById(id);
        if (i != null) {
            return i.getValue();
        } else {
            return "GLOBAL";
        }
    }

    public String formatRelativization() {
        switch (getRelativizationType()) {
            case GLOBAL:
                return "";
            case RELATIVIZED:
                return String.format("{%s}", formatIndividual(sender));
            case DIRECTED:
                return String.format("{%s,%s}", formatIndividual(sender), formatIndividual(receiver));
        }
        return super.toString();
    }

    public String formatComposition() {
        if (composition != null) {
            return String.format(" %s %s", composition.getType(), composition.getOther());
        }
        return "";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.sender;
        hash = 37 * hash + this.receiver;
        hash = 37 * hash + Objects.hashCode(this.relativizationType);
        hash = 37 * hash + Objects.hashCode(this.value);
        hash = 37 * hash + Objects.hashCode(this.composition);
        hash = 37 * hash + Objects.hashCode(this.action);
        return hash;
    }

    @Override
    public abstract boolean equals(Object obj);

    protected boolean equalsBase(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Clause other = (Clause) obj;
        if (!Objects.equals(this.sender, other.sender)) {
            return false;
        }
        if (!Objects.equals(this.receiver, other.receiver)) {
            return false;
        }
        if (this.relativizationType != other.relativizationType) {
            return false;
        }
        if (!Objects.equals(this.composition, other.composition)) {
            return false;
        }
        if (!Objects.equals(this.action, other.action)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public abstract Object clone();

    //<editor-fold defaultstate="collapsed" desc="properties">
    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public RelativizationType getRelativizationType() {
        return relativizationType;
    }

    public void setRelativizationType(RelativizationType relativizationType) {
        this.relativizationType = relativizationType;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public ClauseComposition getComposition() {
        return composition;
    }

    public void setComposition(ClauseComposition composition) {
        this.composition = composition;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

//</editor-fold>
    public Clause getTail() {
        if (composition == null) {
            return this;
        }
        Clause tail = composition.getOther();
        while (tail.composition != null) {
            tail = tail.composition.getOther();
        }
        return tail;
    }

    public boolean contains(ClauseCompositionType t, Clause c) {       
        Clause my = (Clause) this.clone();
        my.setComposition(null);
        if (my.equals(c)) {
            return true;
        }
        if (composition != null) {
            if (composition.getType() != t) {
                return false;
            }
            Clause other = composition.getOther();
            if (other != null) {
                return other.contains(t, c);
            }
        }
        return false;
    }
}
