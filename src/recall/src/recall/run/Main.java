/*
 * 
 */
package recall.run;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import recall.algorithms.AutomataConstructor;
import recall.model.automata.Automaton;
import recall.model.automata.State;
import recall.model.automata.Transition;
import recall.model.contracts.Contract;
import recall.parser.ContractLoader;
import recall.parser.SymbolTable;
import recall.util.AutomatonExporter;
import recall.util.FileUtil;
import recall.util.LogType;
import recall.util.Logger;
import recall.util.RunConfiguration;

/**
 *
 * @author wellington
 */
public class Main {

    public static void main(String[] args) {
        /* USED FOR TESTS IN IDE */
//         RunConfiguration config = RunConfiguration.getConfig("c.rcl"); 
//         ContractLoader.setShowParseTree(true);
        /* ***************** */
        RunConfiguration config = CommandLineUtil.parseParams(args);
        Logger.configure(config);
        Logger.getInstance().log(LogType.ADDITIONAL, "Using " + config);
        Logger.getInstance().log(LogType.MINIMAL, "Analysing contract in " + config.getContractFileName());
        try {
            Contract contract = ContractLoader.loadFromFile(config.getContractFileName());
            Logger.getInstance().log(LogType.NECESSARY, "Loaded Contract: " + contract);
            Logger.getInstance().log(LogType.ADDITIONAL, SymbolTable.getInstance().toString());
            Logger.getInstance().log(LogType.MINIMAL, "Processing contract...");
            long init = System.currentTimeMillis();
            Automaton automaton = new AutomataConstructor(config).process(contract);
            Logger.getInstance().log(LogType.MINIMAL, printResult(automaton, System.currentTimeMillis() - init));

            if (config.isExportDecompositions()) {
                Logger.getInstance().log(LogType.MINIMAL, "Exporting Decompositions to " + config.getDecompositionsFileName());
                FileUtil.writeToFile(config.getDecompositionsFileName(), AutomatonExporter.dumpStates(automaton));
            }
            if (config.isExportAutomaton()) {
                Logger.getInstance().log(LogType.MINIMAL, "Exporting Automaton to " + config.getAutomatonFileName());
                if (config.isExportMinAutomaton()){
                    FileUtil.writeToFile(config.getAutomatonFileName()+"__min", AutomatonExporter.dumpToMinDot(automaton));    
                }
                FileUtil.writeToFile(config.getAutomatonFileName(), AutomatonExporter.dumpToDot(automaton));
            }
        } catch (Exception ex) {
            Logger.getInstance().log(LogType.MINIMAL, "A fatal error ocurred");
            Logger.getInstance().logException(ex);
            if (ex instanceof ParseCancellationException) {
                Logger.getInstance().log(LogType.MINIMAL, "Malformed Contract, please check the syntax.");
            }
        } finally {
            Logger.terminate();
        }
    }

    private static String printTrace(Automaton automaton) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n-------------------------------------------------------\n");
        automaton.getConflicts().forEach(s -> {
            sb.append(String.format("Conflict found in state (s%d)\n", s.getId()));
            sb.append("Conflict: ").append(s.getConflictInformation()).append("\n");
            sb.append("-------------------------------------------------------\n");
            StringBuilder sb0 = new StringBuilder("Trace: ");
            StringBuilder sb1 = new StringBuilder("Stacktrace: \n");
            State prev = s;
            do {
                if (prev.getTrace().isEmpty()) {
                    break;
                }
                Transition t = prev.getTrace().firstElement();
                prev = t.getFrom();
                sb0.append(String.format("(s%d)", t.getTo().getId()));
                sb1.append(String.format("%s(s%d)%s - %s \n", ConsoleUtil.FG_YELLOW, t.getTo().getId(), ConsoleUtil.RESET, t.getTo().getClause()));
                sb1.append(String.format("%s<T%d> - %s\n", ConsoleUtil.FG_RED, t.getId(), ConsoleUtil.FG_BLUE + t.getActions() + ConsoleUtil.RESET));
                sb0.append(String.format("<--T%d--", t.getId()));
            } while (prev != null);
            if (prev != null) {
                sb0.append(String.format("(s%d)", prev.getId()));
                sb1.append(String.format("%s(s%d)%s - %s \n", ConsoleUtil.FG_YELLOW, prev.getId(), ConsoleUtil.RESET, prev.getClause()));
            }
            sb0.append("\n");
            sb.append(sb0.toString());
            sb.append("-------------------------------------------------------\n");
            sb.append(sb1.toString());
            sb.append("-------------------------------------------------------\n");

        });
        return sb.toString();
    }

    private static String printResult(Automaton automaton, long ms) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Completed in %dms\n", ms));
        if (automaton.isConflictFound()) {
            sb.append(ConsoleUtil.FG_RED).append("[CONFLICT] ").append(ConsoleUtil.FG_WHITE).append("A conflict was found in the analyzed contract.").append(ConsoleUtil.RESET).append("\n");
            sb.append(printTrace(automaton)).append("\n");
        } else {
            sb.append(ConsoleUtil.FG_GREEN + "[CONFLICT-FREE] " + ConsoleUtil.FG_WHITE + "The analyzed contract is conflict-free." + ConsoleUtil.RESET).append("\n");
        }
        return sb.toString();
    }

}
