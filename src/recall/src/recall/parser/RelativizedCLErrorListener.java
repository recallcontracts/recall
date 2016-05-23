/*
 * 
 */
package recall.parser;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.Pair;

/**
 *
 * @author wellington
 */
@SuppressWarnings("unchecked")
public class RelativizedCLErrorListener implements ANTLRErrorListener{

    private final List<Pair<String, RecognitionException>> errors;

    public List<Pair<String, RecognitionException>> getErrors() {
        return errors;
    }

    public RelativizedCLErrorListener() {
        errors = new ArrayList<>();
    }
    
    @Override
    public void syntaxError(Recognizer<?, ?> rcgnzr, Object o, int i, int i1, String string, RecognitionException re) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ERROR] Malformed Contract\n");
        sb.append(String.format("-> Syntax Error in line %d column %d.\n", i, i1));
        sb.append(String.format("-> Error Message: %s.\n",string));
        errors.add(new Pair(sb.toString(), re));
        
    }

    @Override
    public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean bln, BitSet bitset, ATNConfigSet atncs) {
        //System.out.println("reportAmbiguity");
    }

    @Override
    public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitset, ATNConfigSet atncs) {
        //System.out.println("reportAttemptingFullContext");
        
    }

    @Override
    public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atncs) {
        //System.out.println("reportContextSensitivity");
    }
    
}
