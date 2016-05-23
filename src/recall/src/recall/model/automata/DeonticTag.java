/*
 * 
 */
package recall.model.automata;

import java.io.Serializable;
import java.util.Objects;
import recall.model.actions.BasicAction;
import recall.model.contracts.DeonticClauseType;
import recall.model.contracts.RelativizationType;
import recall.parser.SymbolTable;

/**
 *
 * @author wellington
 */
public class DeonticTag  implements Serializable{

    private DeonticClauseType type;
    private BasicAction action;
    private RelativizationType relativization;
    private Integer sender;
    private Integer receiver;

    public DeonticTag() {
    }

    public DeonticTag(DeonticClauseType type, BasicAction action, RelativizationType relativization, Integer sender, Integer receiver) {
        this.type = type;
        this.action = action;
        this.relativization = relativization;
        this.sender = sender;
        this.receiver = receiver;
    }

    public DeonticTag(DeonticClauseType type, BasicAction action, Integer sender, Integer receiver) {
        this(type, action, RelativizationType.DIRECTED, sender, receiver);
    }

    public DeonticTag(DeonticClauseType type, BasicAction action, Integer sender) {
        this(type, action, RelativizationType.RELATIVIZED, sender, -1);
    }

    public DeonticTag(DeonticClauseType type, BasicAction action) {
        this(type, action, RelativizationType.GLOBAL, -1, -1);
    }

    public BasicAction getAction() {
        return action;
    }

    public void setAction(BasicAction action) {
        this.action = action;
    }

    public DeonticClauseType getType() {
        return type;
    }

    public void setType(DeonticClauseType type) {
        this.type = type;
    }

    public RelativizationType getRelativization() {
        return relativization;
    }

    public void setRelativization(RelativizationType relativization) {
        this.relativization = relativization;
    }

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.type);
        hash = 59 * hash + Objects.hashCode(this.action);
        hash = 59 * hash + Objects.hashCode(this.relativization);
        hash = 59 * hash + Objects.hashCode(this.sender);
        hash = 59 * hash + Objects.hashCode(this.receiver);
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
        final DeonticTag other = (DeonticTag) obj;
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.action, other.action)) {
            return false;
        }
        if (this.relativization != other.relativization) {
            return false;
        }
        if (!Objects.equals(this.sender, other.sender)) {
            return false;
        }
        if (!Objects.equals(this.receiver, other.receiver)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        switch (relativization) {
            case DIRECTED:
                return String.format("%s(%s,%s,%s)", type.getSymbol(), SymbolTable.getInstance().getSymbolById(sender).getValue(),
                        action, SymbolTable.getInstance().getSymbolById(receiver).getValue());
            case RELATIVIZED:
                return String.format("%s(%s,%s)", type.getSymbol(), SymbolTable.getInstance().getSymbolById(sender).getValue(),action);
            case GLOBAL:
                return String.format("%s(%s)", type.getSymbol(), action);
        }
        return "";
    }

}
