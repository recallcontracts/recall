package recall.model.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import recall.parser.Symbol;
import recall.parser.SymbolTable;

/**
 *
 * @author wellington
 */
public class BasicAction implements Action {

    public static final BasicAction SKIP = new BasicAction(0, false, true, false);
    public static final BasicAction VIOLATION = new BasicAction(-1, true, false, false);

    private int value;
    private boolean violation;
    private boolean skip;
    private boolean negation;

    private List<BasicAction> concurrentActions;

    public BasicAction(int value, boolean violation, boolean skip, boolean negation) {
        this.value = value;
        this.violation = violation;
        this.skip = skip;
        this.negation = negation;
        concurrentActions = new ArrayList<>();
    }

    public BasicAction(int value) {
        this(value, false, false, false);
    }

    @Override
    public String toString() {
        return formatAction(getValue());
    }

    //<editor-fold defaultstate="collapsed" desc="properties">
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isViolation() {
        return violation;
    }

    public void setViolation(boolean violation) {
        this.violation = violation;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public boolean isNegation() {
        return negation;
    }

    public void setNegation(boolean negation) {
        this.negation = negation;
    }

    public List<BasicAction> getConcurrentActions() {
        return concurrentActions;
    }

//</editor-fold>
    private String formatAction(int value) {
        if (violation) {
            return "0";
        }
        if (skip || value == 0) {
            return "1";
        }
        Symbol s = SymbolTable.getInstance().getSymbolById(value);
        return (negation ? "!" : "") + (s != null ? s.getValue() : "UNDEF");
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.value;
        hash = 47 * hash + (this.violation ? 1 : 0);
        hash = 47 * hash + (this.skip ? 1 : 0);
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
        final BasicAction other = (BasicAction) obj;
        if (this.violation != other.violation) {
            return false;
        }
        if (this.skip != other.skip) {
            return false;
        }
        return this.value == other.value;
    }

    @Override
    public Object clone() {
        return new BasicAction(this.value, this.violation, this.skip, this.negation);
    }

    @Override
    public List<Action> getBasicActions() {
        ArrayList<Action> list = new ArrayList<>();
        if (!isSkip()) {
            list.add((Action) new BasicAction(this.value));
        }
        list.addAll(concurrentActions);
        return list;
    }

}
