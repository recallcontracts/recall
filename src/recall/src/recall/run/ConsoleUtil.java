/*
 * 
 */
package recall.run;

import java.util.Enumeration;
import java.util.Properties;
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
        System.out.println("Runtime.getRuntime().availableProcessors(): "+Runtime.getRuntime().availableProcessors());
        System.out.println("Runtime.getRuntime().freeMemory(): "+Runtime.getRuntime().freeMemory());
        System.out.println("Runtime.getRuntime().maxMemory(): "+ Runtime.getRuntime().maxMemory());        
        while (System.getProperties().elements().hasMoreElements()){
            System.out.println(System.getProperties().elements().nextElement());
        }
        
    }

}
