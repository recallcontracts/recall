package recall.model.contracts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import recall.model.actions.BasicAction;

/**
 *
 * @author wellington
 */
public class Contract implements Serializable{

    private ArrayList<Conflict> globalConflicts;
    private ArrayList<Conflict> relativizedConflicts;

    private Set<Integer> individuals;
    private Set<BasicAction> actions;

    private Set<Clause> clauses;

    public Contract() {
        this.clauses = new HashSet<>();
        globalConflicts = new ArrayList<>();
        relativizedConflicts = new ArrayList<>();
        individuals = new HashSet<>();
        actions = new HashSet<>();
    }

    public Contract(Clause... clauseList) {
        this.clauses = new HashSet<>(Arrays.asList(clauseList));
        globalConflicts = new ArrayList<>();
        relativizedConflicts = new ArrayList<>();
        individuals = new HashSet<>();
        actions = new HashSet<>();
    }

    public boolean addClause(Clause c) {
        return clauses.add(c);
    }
    //<editor-fold defaultstate="collapsed" desc="properties">
    
    public Set<Clause> getClauses() {
        return clauses;
    }
    
    public void setClauses(Set<Clause> clauses) {
        this.clauses = clauses;
    }
    
    public ArrayList<Conflict> getGlobalConflicts() {
        return globalConflicts;
    }
    
    public void setGlobalConflicts(ArrayList<Conflict> globalConflicts) {
        this.globalConflicts = globalConflicts;
    }
    
    public ArrayList<Conflict> getRelativizedConflicts() {
        return relativizedConflicts;
    }
    
    public void setRelativizedConflicts(ArrayList<Conflict> relativizedConflicts) {
        this.relativizedConflicts = relativizedConflicts;
    }
    
    public void setConflicts(ArrayList<Conflict> globalConflicts, ArrayList<Conflict> relativizedConflicts) {
        setGlobalConflicts(globalConflicts);
        setRelativizedConflicts(relativizedConflicts);
    }
    
    public ArrayList<Conflict> getConflicts() {
        ArrayList<Conflict> list = new ArrayList<>();
        list.addAll(globalConflicts);
        list.addAll(relativizedConflicts);
        return list;
    }
    
    public Set<BasicAction> getActions() {
        return actions;
    }
    
    public void setActions(Set<BasicAction> actions) {
        this.actions = actions;
    }
    
    public Set<Integer> getIndividuals() {
        return individuals;
    }
    
    public void setIndividuals(Set<Integer> individuals) {
        this.individuals = individuals;
    }
//</editor-fold>
   
    @Override
    public String toString() {
        if (clauses != null && clauses.size() > 0) {
            StringBuilder sb = new StringBuilder("\n");
            clauses.forEach(c -> sb.append(String.format("  %s\n", c.toString())));
            sb.append("Conflicts: \n");
            sb.append("\tGlobal:").append(globalConflicts.toString()).append("\n");
            sb.append("\tRelativized:").append(relativizedConflicts.toString()).append("\n");
            return sb.toString();
        }
        return "Empty contract";
    }

    public Clause getFullContract() {
        Clause head = null;
        for (Clause c: clauses){
            if (head == null){
                head = (Clause) c.clone();
            }else{
                head.getTail().setComposition(new ClauseComposition(ClauseCompositionType.AND, (Clause) c.clone()));
            }            
        }
        return head;
    }

}
