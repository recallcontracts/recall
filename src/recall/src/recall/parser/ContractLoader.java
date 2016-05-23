/*
 * 
 */
package recall.parser;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.gui.TreeViewer;
import recall.model.actions.BasicAction;
import recall.model.contracts.Contract;

/**
 *
 * @author wellington
 */
public class ContractLoader {

    private static boolean showParseTree = false;

    public static void setShowParseTree(boolean showParseTree) {
        ContractLoader.showParseTree = showParseTree;
    }

    public static boolean isShowParseTree() {
        return showParseTree;
    }

    public static Contract loadFromFile(String filename) throws FileNotFoundException, IOException, MalformedContractException {
        return loadFromFile(new File(filename));
    }

    public static Contract loadFromFile(File file) throws FileNotFoundException, IOException, MalformedContractException {
        return load(new ANTLRInputStream(new FileInputStream(file)));
    }

    private static Contract load(ANTLRInputStream input) throws MalformedContractException {
        //SymbolsTable.getInstance().clear();
        RelativizedCLLexer lexer = new RelativizedCLLexer(input);
        RelativizedCLParser parser = new RelativizedCLParser(new CommonTokenStream(lexer));
        RelativizedCLErrorListener errorListener = new RelativizedCLErrorListener();
        parser.addErrorListener(errorListener);
        parser.setErrorHandler(new BailErrorStrategy());
        RelativizedCLParser.ContractContext aContract = parser.contract();
        if (showParseTree) {
            showParseTreeFrame(aContract, parser);
        }
        if (errorListener.getErrors().size() > 0) {
            throw new MalformedContractException(errorListener.getErrors());
        } else {
            return loadSymbols(aContract.c);
        }
    }

    public static Contract loadFromText(String text) throws MalformedContractException {
        return load(new ANTLRInputStream(text));
    }

    private static void showParseTreeFrame(ParseTree tree, RelativizedCLParser parser) throws HeadlessException {
        JFrame frame = new JFrame("CONTRACT: " + tree.getText());
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(
                parser.getRuleNames()), tree);
        viewr.setScale(1);
        panel.add(viewr);
        frame.add(panel);
        frame.setSize(1000, 600);
        frame.setState(JFrame.MAXIMIZED_HORIZ);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static Contract loadSymbols(Contract c) {
        for (Symbol s : SymbolTable.getInstance().getDictionary()){
            if (s.getType() == SymbolType.action){
                c.getActions().add(new BasicAction(s.getId()));
            }else if (s.getType() == SymbolType.individual){
                c.getIndividuals().add(s.getId());
            }
        }
        if (c.getIndividuals().isEmpty()){ //all clauses are global
            int i = SymbolTable.getInstance().addSymbol("i", SymbolType.individual);
            c.getIndividuals().add(i);
        }
        return c;
    }
}
