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

    private Map<String, List<String>> criteriaMap = new HashMap<String, List<String>>();

    public Map<String, List<String>> getCriteriaMap() {
        return criteriaMap;
    }

    public void setCriteriaMap(Map<String, List<String>> criteriaMap) {
        this.criteriaMap = criteriaMap;
    }
    
    
}
