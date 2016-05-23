/*
 * 
 */
package recall.parser;

import java.util.List;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.misc.Pair;

/**
 *
 * @author wellington
 */
public class MalformedContractException extends Exception {

    private List<Pair<String, RecognitionException>> syntaxErrors;

    public List<Pair<String, RecognitionException>> getSyntaxErrors() {
        return syntaxErrors;
    }
    
    public MalformedContractException() {
        super();
    }

    public MalformedContractException(List<Pair<String, RecognitionException>> syntaxErrors) {
        this();
        this.syntaxErrors = syntaxErrors;
    }

    
    
}
