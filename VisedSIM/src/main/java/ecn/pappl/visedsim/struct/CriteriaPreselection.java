/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.struct;

import java.util.HashMap;
import java.util.Map;

/**
 * Java Bean representing a selection of criteria.
 *
 * @author bastien
 */
public class CriteriaPreselection {

    /**
     * Field representing the preselection of criteria. The key of the map is
     * the name of the criteria and the value says if the the criteria is
     * selected.
     */
    private Map<String, Boolean> map;

    public CriteriaPreselection() {
        super();
        map = new HashMap<String, Boolean>();
    }

    public Map<String, Boolean> getMap() {
        return map;
    }

    public void setMap(Map<String, Boolean> map) {
        this.map = map;
    }
}
