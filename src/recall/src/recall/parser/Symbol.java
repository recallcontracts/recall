/*
 * 
 */
package recall.parser;

import java.util.Objects;

/**
 *
 * @author wellington
 */
public class Symbol {
    private static int id_base = 0;
    
    private int id;
    private String value;
    private SymbolType type;

    public Symbol() {
    }

    public Symbol(int id, String value, SymbolType type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    //<editor-fold defaultstate="collapsed" desc="properties">
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
    
    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * @return the type
     */
    public SymbolType getType() {
        return type;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(SymbolType type) {
        this.type = type;
    }
    
//</editor-fold>

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.value);
        hash = 17 * hash + Objects.hashCode(this.type);
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
        final Symbol other = (Symbol) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%d | %s | %s \n", id, value, type);
    }
    
    
    
}
