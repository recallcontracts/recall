/*
 * 
 */
package recall.model.contracts;

/**
 *
 * @author wellington
 */
public enum DeonticClauseType {
    OBLIGATION("O"), PERMISSION("P"), PROHIBITION("F");

    private DeonticClauseType(String symbol) {
        this.symbol = symbol;
    }    
    
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
}
