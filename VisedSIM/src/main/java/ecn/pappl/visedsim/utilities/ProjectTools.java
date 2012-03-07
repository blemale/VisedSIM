/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.utilities;

import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.struct.Project;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author bastien
 */
public final class ProjectTools {

    public static Project applyCriteriaPreselection(Project project,
            CriteriaPreselection criteriaPreselection) throws
            IllegalArgumentException, IllegalAccessException {
        Project newProject = new Project();
        Field[] fields = project.getClass().getDeclaredFields();
        Map<String, Boolean> map = criteriaPreselection.getMap();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (map.containsKey(field.getName()) && ((Boolean) map.get(field.
                    getName()))) {
                field.setAccessible(true);
                Object o = field.get(project);
                field.set(newProject, o);
            }
        }
        return newProject;
    }

    public static int getNumberOfCriteriaLines(Project project,
            CriteriaPreselection cp) throws IllegalArgumentException,
            IllegalAccessException {
        int count = 0;
        Map<String, Boolean> map = cp.getMap();
        Field[] fields = project.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (map.containsKey(field.getName()) && ((Boolean) map.get(field.
                    getName()))) {
                if (field.getType().equals(String.class)) {
                    count++;
                } else if (field.getType().equals(List.class)) {
                    field.setAccessible(true);
                    List l = (List) field.get(project);
                    count += l.size();
                }
            }
        }
        return count;
    }

    public static void fillArrayWithSelectedCriteria(Project project,
            CriteriaPreselection cp, Object[][] array) throws
            IllegalArgumentException, IllegalAccessException {
        int indexLine = 0;
        Map<String, Boolean> map = cp.getMap();
        Field[] fields = project.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (map.containsKey(field.getName()) && ((Boolean) map.get(field.
                    getName()))) {
                if (field.getType().equals(String.class)) {
                    array[indexLine][1] = field.getName();
                    field.setAccessible(true);
                    array[indexLine][2] = (String) field.get(project);
                    indexLine++;
                } else if (field.getType().equals(List.class)) {
                    field.setAccessible(true);
                    List l = (List) field.get(project);
                    for (Object o : l) {
                        array[indexLine][1] = field.getName();
                        array[indexLine][2] = (String) o;
                    }
                }
            }
        }
    }
}
