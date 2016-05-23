/*
 * 
 */
package recall.model.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author wellington
 */
public class ComposedAction implements Action {

    private Action left;
    private Action right;
    private ActionOperator operator;

    public ComposedAction(Action left, Action right, ActionOperator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public ComposedAction() {
        right = left = null;
        operator = ActionOperator.NONE;
    }

    @Override
    public List<Action> getBasicActions() {
        List<Action> actions = new ArrayList<>();
        if (left != null) {
            actions.addAll(left.getBasicActions());
        }
        if (right != null) {
            actions.addAll(right.getBasicActions());
        }
        return actions;
    }

    @Override
    public String toString() {
        if (left == null) {
            return String.format("%s", right);
        }

        if (operator == ActionOperator.STAR) {
            return left.toString() + operator;
        }

        if (operator == ActionOperator.NEGATION) {
            return operator + left.toString();
        }

        return String.format("(%s %s %s)", left, operator, right);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.left);
        hash = 89 * hash + Objects.hashCode(this.right);
        hash = 89 * hash + Objects.hashCode(this.operator);
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
        final ComposedAction other = (ComposedAction) obj;
        if (!Objects.equals(this.left, other.left)) {
            return false;
        }
        if (!Objects.equals(this.right, other.right)) {
            return false;
        }
        return this.operator == other.operator;
    }

    @Override
    public Object clone() {
        Action l = null, r = null;
        if (left!=null)
            l = (Action) left.clone();
        if (right!=null)
            r = (Action) right.clone();
        return new ComposedAction(l, r, operator);
    }

    //<editor-fold defaultstate="collapsed" desc="properties">
    public Action getLeft() {
        return left;
    }

    public void setLeft(Action left) {
        this.left = left;
    }

    public Action getRight() {
        return right;
    }

    public void setRight(Action right) {
        this.right = right;
    }

    public ActionOperator getOperator() {
        return operator;
    }

    public void setOperator(ActionOperator operator) {
        this.operator = operator;
    }

//</editor-fold>
}
