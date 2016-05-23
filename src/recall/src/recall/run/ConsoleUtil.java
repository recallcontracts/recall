/*
 * 
 */
package recall.run;

import recall.algorithms.ClauseDecomposer;
import recall.model.contracts.Clause;
import recall.model.contracts.Contract;
import recall.parser.ContractLoader;
import recall.parser.MalformedContractException;

/**
 *
 * @author wellington
 */
public class ConsoleUtil {

    public static final String RESET = "\u001B[0m";

    public static final String FG_BLACK = "\u001B[30m";
    public static final String FG_RED = "\u001B[31m";
    public static final String FG_GREEN = "\u001B[32m";
    public static final String FG_YELLOW = "\u001B[33m";
    public static final String FG_BLUE = "\u001B[34m";
    public static final String FG_PURPLE = "\u001B[35m";
    public static final String FG_CYAN = "\u001B[36m";
    public static final String FG_WHITE = "\u001B[37m";

    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";

    public static void main(String[] args) throws MalformedContractException {
        Contract contract = ContractLoader.loadFromText("{i,j}[a&b]({j,k}F(c));");
        Clause c1 = contract.getFullContract();
        System.out.println(c1);
        System.out.println();
        Clause c2 = ClauseDecomposer.processComposedActions(c1);
        System.out.println(c2);
        
        Clause c3 = ClauseDecomposer.processComposedActions(c2);
        System.out.println(c3);

        Clause c4 = ClauseDecomposer.processComposedActions(c3);
        System.out.println(c4);

        Clause c5 = ClauseDecomposer.processComposedActions(c4);
        System.out.println(c5);
    }

}
