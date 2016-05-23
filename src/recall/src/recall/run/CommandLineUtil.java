/*
 * 
 */
package recall.run;

import com.google.common.io.Files;
import java.io.File;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import recall.util.LogLevel;
import recall.util.RunConfiguration;

/**
 * Provides methods to parse the tool's command line options.
 *
 * @author wellington
 */
public class CommandLineUtil {

    public static final String TOOL_NAME    = "recall";
    public static final String VERSION      = "v1.0";
    public static final String TOOL_DESC    = "recall - RelativizEd ContrAct Language anaLyser (" + VERSION + ") \n\n";
    public static final String GLOBAL_LOG   = TOOL_NAME + ".log";
    public static final String MAIL         = "della.mura@gmail.com";
    public static final String SITE         = "http://recall.della-mura.com.br";

    public static RunConfiguration parseParams(String[] args) {
        RunConfiguration config = new RunConfiguration();
        Options options = getCommandLineOptions();
        CommandLineParser parser = new DefaultParser();
        try {
            options.addOption(new Option("m", "Min"));
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("h")) {
                printUsage(options);
//            } else if (line.hasOption("i")) {
//                System.out.println(getVersionInfo());
//                System.exit(0);
//            } else if (line.hasOption("x")) {
//                System.out.println(getSyntax());
//                System.exit(0);
            } else {
                if (line.getArgs().length > 0) {
                    config.setContractFileName(line.getArgs()[0]);
                    config.setResultFileName(getFileName(config.getContractFileName()) + ".result");
                } else {
                    printUsage(options);
                }

                config.setExportAutomaton(line.hasOption("g"));
                String gf = line.getOptionValue("g");
                config.setAutomatonFileName((gf == null ? getFileName(config.getContractFileName()) + ".dot" : gf));
                config.setExportDecompositions(config.isExportAutomaton());
                config.setDecompositionsFileName((gf == null ? getFileName(config.getContractFileName()) : getFileName(gf)) + ".csv");

                config.setLogLevel(line.hasOption("v") ? LogLevel.VERBOSE : LogLevel.NORMAL);
//                if (line.hasOption("l")) {
//                    config.setGlobalLogFilename(line.getOptionValue("l"));
//                } else {
                    config.setGlobalLogFilename(GLOBAL_LOG);
//                }
                config.setUsePrunning(!line.hasOption("n"));
                //config.setUseSelfActions(line.hasOption("s"));
                config.setContinueOnConflict(line.hasOption("c"));
                config.setExportMinAutomaton(line.hasOption("m"));
            }
        } catch (ParseException exp) {
            printUsage(options, "Invalid parameters.  Reason: " + exp.getMessage());
        }
        return config;
    }

    private static Options getCommandLineOptions() {
        Options allopts = new Options();

        allopts.addOption(new Option("h", "help", false, "Print this message and exit."));
//        allopts.addOption(new Option("i", "version-info", false, "Print the version information and exit."));
//        allopts.addOption(new Option("x", "syntax", false, "Print a compact documentation of the contracts syntax and exit."));

        allopts.addOption(new Option("v", "verbose", false, "Turn on the verbose mode."));
        
//        Option f = new Option("l", "global-log-file", true, "Defines a global log file instead the usual file <"+GLOBAL_LOG+">.");
//        f.setArgName("global.log");
//        f.setOptionalArg(false);
//        allopts.addOption(f);

        allopts.addOption(new Option("n", "no-prunning", false, "Don't use the prunning method."));
                
        allopts.addOption(new Option("c", "continue", false, "Continues the analysis if a conflict is found."));
        
        Option g = new Option("g", true, "Exports the automaton into a graphviz file."
                + "\nDefault filename is <CONTRACT_FILE>.dot.");
        g.setArgName("filename.dot");
        g.setOptionalArg(true);
        allopts.addOption(g);

        return allopts;
    }

    private static void printUsage(Options options, String... messages) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(80, TOOL_NAME + " <CONTRACT_FILE> [OPTIONS]", TOOL_DESC + "[OPTIONS]", options, getFooter());
        System.out.println();
        if (messages.length > 0) {
            System.out.print("\n\n[SORRY] An error ocurred. ");
            for (String s : messages) {
                System.out.println(s);
            }
        }
        System.out.println();
        System.exit(0);
    }

    private static String getFileName(String filename) {
        int lastIndexOf = filename.lastIndexOf(File.separatorChar);
        if (lastIndexOf > 0) {
            return filename.substring(0, lastIndexOf) + File.separator + Files.getNameWithoutExtension(filename);
        }
        return Files.getNameWithoutExtension(filename);
    }

    private static String getFooter() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nUsage Examples: \n")
                .append(String.format("\"%s contract.rcl\" analyzes a contract in the file 'contract.rcl'.\n", TOOL_NAME))
                .append(String.format("\"%s contract.rcl -g\" analyzes the contract and writes automaton in a file.\n", TOOL_NAME))
//                .append(String.format("\"%s contract.rcl -c\" analyzes the 'contract.rcl' and continue the analysis if a conflict is found.\n", TOOL_NAME))
                .append("\nPlease report issues to ").append(MAIL)
                .append("\nMore information about this tool in ").append(SITE);
        return sb.toString();
    }

//    private static String getSyntax() {
//        return "/* The rClan syntax suports comments in any place in contract code.\n"
//                + "   The comments can be /* in line */ or //one line */\n"
//                + "\n"
//                + "<contract> ::= <conflict> <clause> (;<clause>)* ;\n"
//                + "<conflict> ::= CONFLICT { conf-type* };\n"
//                + "<conf-type>::= [GLOBAL|RELATIVIZED] {(ID,ID) (,(ID,ID))*};\n"
//                + "<clause>   ::= <clause> ^ <clause> | <co> | <cp> | <cf> | <cd> | T | F\n"
//                + "<cd>       ::= <rel>[<beta>](<clause>)\n"
//                + "<co>       ::= <rel>O(<alpha>)<penalty> | <co> XOR <co>\n"
//                + "<cp>       ::= <rel>P(<alpha>) | <cp> XOR <cp>\n"
//                + "<cf>       ::= <rel>F(<alpha>)<penalty> | <cd> OR <cf>\n"
//                + "<rel>      ::= { ID } | { ID, ID } | empty\n"
//                + "<penalty>  ::= _/ <clause> /_ | empty\n"
//                + "<alpha>    ::= ID | <ID>+<alpha> | <alpha>&<alpha> | <alpha>.<alpha> | (<alpha>) | 1 | 0\n"
//                + "<beta>     ::= ID | <ID>+<beta> | <ID>&<beta> | <ID>.<beta> | <beta>*| !<beta> | (<beta>) | 1 | 0\n"
//                + "\n"
//                + "### Tokens ###\n"
//                + "+-----------+-----------------------+------------------------------------------------+\n"
//                + "| TOKEN     | Pattern               | Description                                    |\n"
//                + "+-----------+-----------------------+------------------------------------------------+\n"
//                + "| ID        | [a-zA-Z][a-zA-Z0-9_]* | Represents an identifier to action or indivual |\n"
//                + "+-----------+-----------------------+------------------------------------------------+\n"
//                + "| SKIP      | 1                     | An action that means any action                |\n"
//                + "+-----------+-----------------------+------------------------------------------------+\n"
//                + "| VIOLATION | 0                     | An action that means contract violation        |\n"
//                + "+-----------+-----------------------+------------------------------------------------+\n"
//                + "\n"
//                + "### Operators ###\n"
//                + "+-----------+-----------------------------------+\n"
//                + "| Operator  | Description                       |\n"
//                + "+-----------+-----------------------------------+\n"
//                + "|  ^ / AND  | Conjunction of clauses            |\n"
//                + "+-----------+-----------------------------------+\n"
//                + "|  | / OR   | Disjunction of clauses            |\n"
//                + "+-----------+-----------------------------------+\n"
//                + "|  - / XOR  | Exclusive Disjunction of clauses  |\n"
//                + "+-----------+-----------------------------------+\n"
//                + "|     .     | Sequence of actions               |\n"
//                + "+-----------+-----------------------------------+\n"
//                + "|     +     | Choice of actions                 |\n"
//                + "+-----------+-----------------------------------+\n"
//                + "|     &     | Concurrent actions                |\n"
//                + "+-----------+-----------------------------------+\n"
//                + "|     .     | Sequence of actions               |\n"
//                + "+-----------+-----------------------------------+\n"
//                + "|     !     | Negation of an action             |\n"
//                + "+-----------+-----------------------------------+\n"
//                + "|     *     | Repetition of an action           |\n"
//                + "+-----------+-----------------------------------+\n";
//    }

//    private static String getVersionInfo() {
//        StringBuilder sb = new StringBuilder("rClan: ");
//        sb.append("A Relativized Contract Language Analyzer Tool (")
//                .append(VERSION)
//                .append(")\nUse -h option for help.");
//        return sb.toString();
//    }

}
