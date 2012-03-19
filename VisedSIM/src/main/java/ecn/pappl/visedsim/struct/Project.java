/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.struct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java Bean representing an ANR project.
 * <p/>
 * @author bastien
 */
public class Project {

    /**
     * Field representing the informations of the project.
     * The informations are representing by a {@link Map}.
     * The keys are a {@link String} representing the name of the criterion.
     * The values are a {@link  List} of {@link String} representing the
     * different values for a given criterion.
     */
    private Map<String, List<String>> criteriaMap =
            new HashMap<String, List<String>>();

    /**
     * Default constructor.
     */
    public Project() {
        super();
    }

    /**
     * Get the field criteriaMap.
     * <p/>
     * @return the {@link Map}.
     */
    public Map<String, List<String>> getCriteriaMap() {
        return criteriaMap;
    }

    /**
     * Set the field criteriaMap.
     * <p/>
     * @param criteriaMap the  {@link Map} to set.
     */
    public void setCriteriaMap(Map<String, List<String>> criteriaMap) {
        this.criteriaMap = criteriaMap;
    }
}
