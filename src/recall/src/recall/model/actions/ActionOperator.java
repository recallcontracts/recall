/*
 * 
 */
package recall.model.actions;

/**
 *
 * @author wellington
 */
public enum ActionOperator {

    CHOICE, CONCURRENCY, SEQUENCE, NEGATION, STAR, NONE;

    @Override
    public String toString() {
        switch (this) {
            case CHOICE:
                return "+";
            case CONCURRENCY:
                return "&";
            case NEGATION:
                return "!";
            case SEQUENCE:
                return ".";
            case STAR:
                return "*";
            case NONE:
                break;
        }
        return super.toString();
    }

}
