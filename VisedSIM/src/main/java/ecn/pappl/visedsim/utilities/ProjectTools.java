/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.utilities;

import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.struct.Project;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bastien
 */
public final class ProjectTools {
    
    private ProjectTools(){        
    }

    public static Project applyCriteriaPreselection(Project project,
            CriteriaPreselection criteriaPreselection) {
        Map<String, List<String>> criteriaMap = project.getCriteriaMap();

        Map<String, Boolean> criteriaPreselectionMap = criteriaPreselection.
                getMap();

        Project newProject = new Project();
        Map<String, List<String>> newCriteriaMap = newProject.getCriteriaMap();

        for (String criteria : criteriaMap.keySet()) {
            if (criteriaPreselectionMap.containsKey(criteria)
                    && criteriaPreselectionMap.get(criteria)) {
                newCriteriaMap.put(criteria, criteriaMap.get(criteria));
            } 
        }
        return newProject;
    }

    public static int getNumberOfCriteriaLines(Project project,
            CriteriaPreselection cp) {
        int count = 0;

        Map<String, Boolean> criteriaPreselectionMap = cp.getMap();

        Map<String, List<String>> criteriaMap = project.getCriteriaMap();

        for (String criteria : criteriaMap.keySet()) {
            if (criteriaPreselectionMap.containsKey(criteria)
                    && criteriaPreselectionMap.get(criteria)) {
                count += criteriaMap.get(criteria).size();
            }
        }
        return count;
    }

    public static void fillArrayWithSelectedCriteria(Project project,
            CriteriaPreselection cp, Object[][] array) {
        int indexLine = 0;

        Map<String, List<String>> criteriaMap = project.getCriteriaMap();

        Map<String, Boolean> criteriaPreselectionMap = cp.getMap();

        for (String criteria : criteriaMap.keySet()) {
            if (criteriaPreselectionMap.containsKey(criteria)
                    && criteriaPreselectionMap.get(criteria)) {
                List<String> criteriaValueList = criteriaMap.get(criteria);
                for (String criteriaValue : criteriaValueList) {
                    array[indexLine][0] = criteria;
                    array[indexLine][1] = criteriaValue;
                    indexLine++;
                }
            }

        }
    }
}
