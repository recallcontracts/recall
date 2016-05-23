/*
 * 
 */
package recall.model.contracts;

/**
 *
 * @author wellington
 */
public class BooleanClause extends Clause {

    public static final BooleanClause TRUE = new BooleanClause(Boolean.TRUE);
    public static final BooleanClause FALSE = new BooleanClause(Boolean.FALSE);

    public BooleanClause(Boolean value) {
        super(-1, -1, RelativizationType.GLOBAL, value, null, null);
    }

    @Override
    public String toString() {
        if (getValue()) {
            return "T";
        } else {
            return "F";
        }
    }

    @Override
    public Object clone() {
        BooleanClause booleanClause = new BooleanClause(this.getValue());
        if (getComposition() != null) {
            booleanClause.setComposition((ClauseComposition) getComposition().clone());
        }
        return booleanClause;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BooleanClause other = (BooleanClause) obj;

        return this.getValue().equals(other.getValue());
    }

}
