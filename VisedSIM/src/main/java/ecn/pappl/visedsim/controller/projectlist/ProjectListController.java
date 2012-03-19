/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.projectlist;

import ecn.pappl.visedsim.io.ExcelDatasExtractor;
import ecn.pappl.visedsim.io.MapCleaner;
import ecn.pappl.visedsim.io.MapConverter;
import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.struct.ProjectList;
import ecn.pappl.visedsim.utilities.ProjectTools;
import ecn.pappl.visedsim.utilities.XMLPersistanceTools;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * Abstract class representing a controller for a {@link ProjectList}.
 * <p/>
 * @author bastien
 */
public final class ProjectListController implements ProjectListLoader,
        ProjectListSelector, ProjectListExcelLoader, ProjectListSaver {

    private static final String ACRONYM = "acronym";
    /**
     * The {@link ProjectList} to control.
     */
    private ProjectList projectList = null;
    /**
     * Unique instance of {@link ProjectListController}.
     */
    private volatile static ProjectListController instance = null;

    /**
     * The default constructor.
     */
    private ProjectListController() {
        super();
    }

    /**
     * Get the unique instance of {@link ProjectListController}.
     * <p/>
     * @return the unique instance of {@link ProjectListController}.
     */
    public static ProjectListController getInstance() {
        if (ProjectListController.instance == null) {
            synchronized (ProjectListController.class) {
                ProjectListController.instance = new ProjectListController();
            }
        }

        return ProjectListController.instance;
    }

    public ProjectList loadProjectList(String fileName) throws
            FileNotFoundException, IOException {
        this.projectList = (ProjectList) XMLPersistanceTools.decodeFromFile(
                fileName);
        return this.projectList;
    }

    public Project getProjectByAcronym(String acronym) {
        if (this.projectList == null) {
            return null;
        } else {
            for (Project project : this.projectList.getProjectList()) {
                Map<String, List<String>> criteriaMap = project.getCriteriaMap();
                if (criteriaMap.containsKey(ACRONYM) && criteriaMap.get(
                        ACRONYM).get(0).equals(acronym)) {
                    return project;
                }
            }
            return null;
        }
    }

    public List<String> getProjectsAcronyms() {
        if (this.projectList == null) {
            return new LinkedList<String>();
        } else {
            List<String> list = new LinkedList<String>();
            for (Project project : this.projectList.getProjectList()) {
                Map<String, List<String>> criteriaMap = project.getCriteriaMap();
                if (criteriaMap.containsKey(ACRONYM)) {
                    list.add(criteriaMap.get(ACRONYM).get(0));
                }
            }
            Collections.sort(list);
            return list;
        }
    }

    public List<String> getProjectsAcronymsByFirstLetters(String firstLetters) {
        if (this.projectList == null) {
            return new LinkedList<String>();
        } else {
            List<String> list = new LinkedList<String>();
            for (Project project : this.projectList.getProjectList()) {
                Map<String, List<String>> criteriaMap = project.getCriteriaMap();
                if (criteriaMap.containsKey(ACRONYM) && criteriaMap.get(
                        ACRONYM).get(0).startsWith(firstLetters)) {
                    list.add(criteriaMap.get(ACRONYM).get(0));
                }
            }
            Collections.sort(list);
            return list;
        }
    }

    public ProjectList loadExcelProjectList(String fileName,
            Map<String, List<Integer>> columnsOrder,
            int firstCellRow, int firstCellColl)
            throws FileNotFoundException, IOException, InvalidFormatException {
        Map<Integer, Map<Integer, String>> map =
                ExcelDatasExtractor.procFile(new File(fileName));
        for (int i = 0; i < firstCellRow; i++) {
            MapCleaner.removeRow(map, i);
        }
        for (int j = 0; j < firstCellColl; j++) {
            MapCleaner.removeCol(map, j);
        }
        this.projectList = MapConverter.convertMapToProjectList(map,
                columnsOrder);
        return this.projectList;
    }

    public void saveProjectList(String fileName) throws FileNotFoundException,
            IOException {
        if (this.projectList != null) {
            XMLPersistanceTools.encodeToFile(this.projectList, fileName);
        }
    }

    public void saveProjectListWithInterrestConflicts(String fileName,
            Map<String, Boolean> conflicts,
            CriteriaPreselection conflictCriteria) throws FileNotFoundException,
            IOException {
        if (this.projectList != null) {
            ProjectList newProjectList = new ProjectList();
            List<Project> newProjects = newProjectList.getProjectList();
            for (Project project : this.projectList.getProjectList()) {
                Map<String, List<String>> criteriaMap = project.getCriteriaMap();
                if (criteriaMap.containsKey(ACRONYM)
                        && (conflicts.get(criteriaMap.get(ACRONYM).get(0)))) {
                    Project buffer = ProjectTools.applyCriteriaPreselection(
                            project, conflictCriteria);
                    newProjects.add(buffer);
                } else {
                    newProjects.add(project);
                }
            }
            XMLPersistanceTools.encodeToFile(newProjectList, fileName);
        }

    }

    public Project getFirstProject() {
        if (this.projectList != null && this.projectList.getProjectList().size()
                > 0) {
            return this.projectList.getProjectList().get(0);
        } else {
            return null;
        }
    }
}
