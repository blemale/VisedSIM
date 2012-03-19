/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.utilities;

import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.struct.Project;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing a toolkit for {@link Project}.
 * <p/>
 * @author bastien
 */
public final class ProjectTools {

    /**
     * Private constructor.
     */
    private ProjectTools() {
    }

    /**
     * Apply a {@link CriteriaPreselection} on a given {@link Project}.
     * <p/>
     * @param project a given {@link Project}.
     * @param criteriaPreselection the {@link CriteriaPreselection} tp apply.
     * @return a {@link Project} identic to the given {@link Project} but with
     * the given {@link CriteriaPreselection}.
     */
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

    /**
     * Get the number of lines needed to view a given {@link Project} with a
     * given {@link CriteriaPreselection}.
     * <p/>
     * @param project a given {@link Project}.
     * @param cp a given {@link CriteriaPreselection}.
     * @return the number of lines.
     */
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

    /**
     * Get the selected criteria of a given {@link Prooject}.
     * <p/>
     * @param project a given {@link Project}.
     * @param cp a given {@link CriteriaPreselection}.
     * @return the {@link Map} with the selected criteria.
     */
    public static Map<String, List<String>> getSelectedCriteria(Project project,
            CriteriaPreselection cp) {
        Map<String, List<String>> criteriaMap = project.getCriteriaMap();
        Map<String, Boolean> criteriaPreselectionMap = cp.getMap();

        Map<String, List<String>> selectedCriteriaMap =
                new HashMap<String, List<String>>();

        for (String criteria :
                criteriaMap.keySet()) {
            if (criteriaPreselectionMap.containsKey(criteria)
                    && criteriaPreselectionMap.get(criteria)) {
                selectedCriteriaMap.put(criteria, criteriaMap.get(criteria));
            }
        }
        return selectedCriteriaMap;
    }
}
