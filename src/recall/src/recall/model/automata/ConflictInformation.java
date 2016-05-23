/*
 * 
 */
package recall.model.automata;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author wellington
 */
public class ConflictInformation  implements Serializable{
    
    private DeonticTag tag;
    private Set<DeonticTag> conflictingTags;
    private Set<DeonticTag> otherSet;

    public ConflictInformation(DeonticTag tag, Set<DeonticTag> conflictingTags, Set<DeonticTag> otherSet) {
        this.tag = tag;
        this.conflictingTags = conflictingTags;
        this.otherSet = otherSet;
    }

    public Set<DeonticTag> getConflictingTags() {
        return conflictingTags;
    }

    public void setConflictingTags(Set<DeonticTag> conflictingTags) {
        this.conflictingTags = conflictingTags;
    }

    public DeonticTag getTag() {
        return tag;
    }

    public void setTag(DeonticTag tag) {
        this.tag = tag;
    }

    public Set<DeonticTag> getOtherSet() {
        return otherSet;
    }

    public void setOtherSet(Set<DeonticTag> otherSet) {
        this.otherSet = otherSet;
    }

    @Override
    public String toString() {
        return tag + " conflicts with " + conflictingTags;
    }
        
    
    
}
