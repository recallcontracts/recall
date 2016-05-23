/*
 * 
 */
package recall.model.actions;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author wellington
 */
public interface Action extends Cloneable, Serializable {
    public List<Action> getBasicActions();

    public Object clone();
}
