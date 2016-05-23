/*
 * 
 */
package recall.model.contracts;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author wellington
 */
public class ClauseComposition implements Cloneable, Serializable{

    private ClauseCompositionType type;
    private Clause other;

    public ClauseComposition() {
        this(ClauseCompositionType.NONE, null);
    }

    public ClauseComposition(ClauseCompositionType type, Clause other) {
        this.type = type;
        this.other = other;
    }

    @Override
    public String toString() {
        return String.format(" %s %s", type, other);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.type);
        hash = 59 * hash + Objects.hashCode(this.other);
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
        final ClauseComposition other = (ClauseComposition) obj;
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.other, other.other)) {
            return false;
        }
        return true;
    }

    @Override
    protected Object clone() {
        return new ClauseComposition(type, (Clause)other.clone());
    } 
    
    //<editor-fold defaultstate="collapsed" desc="properties">
    public ClauseCompositionType getType() {
        return type;
    }

    public void setType(ClauseCompositionType type) {
        this.type = type;
    }

    public Clause getOther() {
        return other;
    }

    public void setOther(Clause other) {
        this.other = other;
    }

//</editor-fold>
}
