/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.io;

import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.struct.ProjectList;
import ecn.pappl.visedsim.utilities.ReflectionTools;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bastien
 */
public class MapConverter {

    public static Project convertMapToProject(Map<Integer, String> map, Map<String, List<Integer>> columnsOrder) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Project project = new Project();
        for (String column : columnsOrder.keySet()) {
            String methodeName = "set" + column;
            for (Integer columnIndex : columnsOrder.get(column)) {
                String[] methodeArgs = {map.get(columnIndex)};
                ReflectionTools.launchMethod(project, methodeArgs, methodeName);
            }
        }
        return project;
    }

    public static ProjectList convertMapToProjectList(Map<Integer, Map<Integer, String>> map, Map<String, List<Integer>> columnsOrder) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ProjectList projectList = new ProjectList();
        ArrayList<Project> projects = projectList.getProjectList();
        for (Integer rowNumber : map.keySet()) {
            Project project = convertMapToProject(map.get(rowNumber), columnsOrder);
            projects.add(project);
        }
        return projectList;
    }
}
