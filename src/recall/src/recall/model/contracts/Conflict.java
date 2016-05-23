/*
 * 
 */
package recall.model.contracts;

import java.io.Serializable;
import java.util.Objects;
import recall.model.actions.BasicAction;

/**
 *
 * @author wellington
 */
public class Conflict implements Serializable{

    private BasicAction a;
    private BasicAction b;
    private ConflictType type;

    public Conflict() {
    }

    public Conflict(BasicAction a, BasicAction b, ConflictType type) {
        this.a = a;
        this.b = b;
        this.type = type;
    }

    public BasicAction getA() {
        return a;
    }

    public void setA(BasicAction a) {
        this.a = a;
    }

    public BasicAction getB() {
        return b;
    }

    public void setB(BasicAction b) {
        this.b = b;
    }

    public ConflictType getType() {
        return type;
    }

    public void setType(ConflictType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.a);
        hash = 97 * hash + Objects.hashCode(this.b);
        hash = 97 * hash + Objects.hashCode(this.type);
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
        final Conflict other = (Conflict) obj;
        if (this.type != other.type) {
            return false;
        }
        //ensures reflexivity
        if (Objects.equals(this.a, other.a) && Objects.equals(this.b, other.b)) {
            return true;
        } else if (Objects.equals(this.a, other.b) && Objects.equals(this.b, other.a)) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "(" + a + "," + b + ": " + type + ')';
    }
    
    

}
