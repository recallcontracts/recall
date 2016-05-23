/*
 * 
 */
package recall.model.actions;

import java.io.Serializable;
import java.util.Objects;
import recall.parser.Symbol;
import recall.parser.SymbolTable;

/**
 *
 * @author wellington
 */
public class RelativizedAction implements Serializable{
    
    private boolean negation;
    
    private int sender;
    private BasicAction action;
    private int receiver;

    public RelativizedAction(int sender, BasicAction action, int receiver) {
        this.sender = sender;
        this.action = action;
        this.receiver = receiver;
    }

    public RelativizedAction() {
        this(-1,null, -1);
    }

    public static RelativizedAction negation(RelativizedAction a){
        RelativizedAction not = new RelativizedAction(a.sender,new BasicAction(a.getAction().getValue()),a.receiver);
        not.negation = true;
        return not;
    }
    
    //<editor-fold defaultstate="collapsed" desc="properties">
    public int getSender() {
        return sender;
    }
    
    public void setSender(int sender) {
        this.sender = sender;
    }
    
    public BasicAction getAction() {
        return action;
    }
    
    public void setAction(BasicAction action) {
        this.action = action;
    }
    
    public int getReceiver() {
        return receiver;
    }
    
    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }
    
//</editor-fold>

    @Override
    public String toString() {
        String act = action.toString();
        if (negation){
            act = "!"+act;
        }
        Symbol ssender = SymbolTable.getInstance().getSymbolById(sender);
        Symbol sreceiver = SymbolTable.getInstance().getSymbolById(receiver);
        return String.format("(%s, %s, %s)",ssender.getValue(), act , sreceiver.getValue());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.sender;
        hash = 83 * hash + Objects.hashCode(this.action);
        hash = 83 * hash + this.receiver;
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
        final RelativizedAction other = (RelativizedAction) obj;
        if (this.negation != other.negation) {
            return false;
        }
        if (this.sender != other.sender) {
            return false;
        }
        if (!Objects.equals(this.action, other.action)) {
            return false;
        }
        if (this.receiver != other.receiver) {
            return false;
        }
        return true;
    }

    

}
