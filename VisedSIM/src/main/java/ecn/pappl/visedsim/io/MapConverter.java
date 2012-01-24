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
            List<Integer> colList = columnsOrder.get(column);
            String methodeName = "set" + column;
            Object[] methodeArgs = new Object[1];
            if (colList.size() > 1) {
                List<String> valuesOfColumn = new ArrayList<String>();
                for (Integer columnIndex : columnsOrder.get(column)) {
                    String value = map.get(columnIndex);
                    if(value != null){
                        valuesOfColumn.add(map.get(columnIndex));
                    }
                }
                
                methodeArgs[0] = valuesOfColumn;

            } else {
                String value = map.get(colList.get(0));
                if(value != null){
                    methodeArgs[0] = map.get(colList.get(0));
                } else {
                    methodeArgs[0] = "";
                }
               
            }
            ReflectionTools.launchMethod(project, methodeArgs, methodeName);
        }
        return project;
    }

    public static ProjectList convertMapToProjectList(Map<Integer, Map<Integer, String>> map, Map<String, List<Integer>> columnsOrder) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ProjectList projectList = new ProjectList();
        List<Project> projects = projectList.getProjectList();
        for (Integer rowNumber : map.keySet()) {
            Project project = convertMapToProject(map.get(rowNumber), columnsOrder);
            projects.add(project);
        }
        return projectList;
    }
}
