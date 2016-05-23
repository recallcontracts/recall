/*
 * 
 */
package recall.algorithms;

import java.util.List;
import java.util.Set;
import recall.model.actions.RelativizedAction;
import recall.model.automata.Automaton;
import recall.model.automata.State;
import recall.model.automata.StateSituation;
import recall.model.automata.Transition;
import recall.model.contracts.Clause;
import recall.model.contracts.Contract;
import recall.parser.SymbolTable;
import recall.util.ContractUtil;
import recall.util.LogLevel;
import recall.util.LogType;
import recall.util.Logger;
import recall.util.RunConfiguration;

/**
 *
 * @author wellington
 */
public class AutomataConstructor {

    private Automaton automaton;

    private List<Set<RelativizedAction>> concurrentActions;
    private Set<RelativizedAction> relativizedActions;
    private ActionExtractor extractor;

    private ClauseDecomposer decomposer;
    private ConflictSearcher searcher;
    private final RunConfiguration config;
    private Contract currentContract;

    public AutomataConstructor(RunConfiguration config) {
        this.config = config;
    }

    public Automaton process(Contract contract) {
        currentContract = contract;
        relativizedActions = null;
        concurrentActions = null;
        extractor = null;
        if (Logger.getInstance().getLevel() == LogLevel.VERBOSE) {
            Logger.getInstance().log(LogType.ADDITIONAL, "Contract Info: " + dumpContractInfo(contract));
        }
        automaton = new Automaton(contract);
        searcher = new ConflictSearcher(contract.getIndividuals(), currentContract.getConflicts());
        decomposer = new ClauseDecomposer(contract.getIndividuals(), true);
        constructAutomaton(automaton.getInitial());
        currentContract = null;
        return automaton;
    }

    private String dumpContractInfo(Contract contract) {
        StringBuilder sb = new StringBuilder();
        sb.append("Contract: ").append(contract).append("\n");
        sb.append("\nActions:\n");
        contract.getActions().forEach(a -> sb.append(a).append(" "));
        sb.append("\nIndividuals:\n");
        contract.getIndividuals().forEach(i -> sb.append(SymbolTable.getInstance().getSymbolById(i).getValue()).append(" "));
        return sb.toString();
    }

    /**
     * Algorithm 1 - Automaton Construction
     *
     * @param s is a State to be Analyzed
     */
    private void constructAutomaton(State s) {
        Clause c1 = s.getClause();
        if (c1.getValue() == null) {//not evaluated yet            
            if (searcher.hasConflict(s)) {//a conflict whas found
                automaton.setConflictFound(true);
            }
            //no conflicts yet, then proceed  
            //c1 = ContractUtil.processComposedActions(c1);
            List<Set<RelativizedAction>> actions = generateActions(c1);
            for (Set<RelativizedAction> a : actions) {
                if ((automaton.isConflictFound() && !config.isContinueOnConflict()) || s.getSituation() == StateSituation.conflicting) {
                    return;
                }
                Clause c2 = decomposer.decompose((Clause) c1.clone(), a);
                State s1 = automaton.getStateByClause(c2);
                if (s1 != null) {
                    automaton.getTransitions().add(new Transition(s, s1, a));
                } else {
                    s1 = new State(c2);
                    Logger.getInstance().log(LogType.ADDITIONAL, "New State: " + c2);
                    automaton.getStates().add(s1);
                    automaton.getTransitions().add(new Transition(s, s1, a));
                    constructAutomaton(s1);                    
                    s1.getTrace().add(new Transition(s, s1, a));
                }

            }
        } else {//evaluated            
            if (c1.getValue()) {//SAT
                s.setSituation(StateSituation.satisfaction);
            } else {//V
                s.setSituation(StateSituation.violating);
            }
        }
    }

    private List<Set<RelativizedAction>> generateActions(Clause c1) {
        if (config.isUsePrunning()) {
            if (extractor == null) {
                extractor = new ActionExtractor(currentContract.getIndividuals(), currentContract.getConflicts());
            }
            return extractor.calculateConcurrentRelativizedActions(c1);
        } else {
            if (concurrentActions == null) {
                relativizedActions = ContractUtil.calculateRelativizedActions(currentContract.getActions(), currentContract.getIndividuals(), true);
                concurrentActions = ContractUtil.calculateConcurrentRelativizedActions(relativizedActions, currentContract.getConflicts());
            }
            return concurrentActions;
        }
    }

}
