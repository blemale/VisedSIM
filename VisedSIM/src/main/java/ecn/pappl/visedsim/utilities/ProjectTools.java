/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.utilities;

import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.struct.Project;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author bastien
 */
public class ProjectTools {

    Project applyCriteriaPreselection(Project project,
            CriteriaPreselection criteriaPreselection) throws
            IllegalArgumentException, IllegalAccessException {
        Project newProject = new Project();
        Field[] fields = project.getClass().getFields();
        Map<String, Boolean> map = criteriaPreselection.getMap();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (((Boolean) map.get(field.getName())) == true) {
                Object o = field.get(project);
                field.set(newProject, o);
            }
        }
        return newProject;
    }
;
}
