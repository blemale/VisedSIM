/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.io;

import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.struct.ProjectList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class processing the conversion of a given {@link Map} into a {@link ProjectList}.
 * <p/>
 * @author bastien
 */
public final class MapConverter {

    private MapConverter() {
    }

    /**
     * Converts a given {@link Map} into a {@link Project}
     * <p/>
     * @param map A given {@link Map} to convert.
     * @param columnsOrder The order of the column in the given {@link Map}.
     * @return A {@link Project}.
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Project convertMapToProject(Map<Integer, String> map,
            Map<String, List<Integer>> columnsOrder) {
        Project project = new Project();
        Map<String, List<String>> criteriaMap = project.getCriteriaMap();
        for (String criteria : columnsOrder.keySet()) {
            List<Integer> colList = columnsOrder.get(criteria);
            List<String> valueList = new LinkedList<String>();
            for (int colIndex : colList) {
                if (map.containsKey(colIndex)) {
                    valueList.add(map.get(colIndex));
                } else {
                    valueList.add("");
                }
            }
            criteriaMap.put(criteria, valueList);
        }
        return project;
    }

    /**
     * Converts a given {@link Map} into a {@link ProjectList}
     * <p/>
     * @param map A given {@link Map} to convert.
     * @param columnsOrder The order of the column in the given {@link Map}.
     * @return A {@link ProjectList}.
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static ProjectList convertMapToProjectList(
            Map<Integer, Map<Integer, String>> map,
            Map<String, List<Integer>> columnsOrder) {
        ProjectList projectList = new ProjectList();
        List<Project> projects = projectList.getProjectList();
        for (Integer rowNumber : map.keySet()) {
            Project project = convertMapToProject(map.get(rowNumber),
                    columnsOrder);
            projects.add(project);
        }
        return projectList;
    }
}
