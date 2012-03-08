/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.projectviewers;

import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.utilities.ProjectTools;

/**
 *
 * @author bastien
 */
public class SwingProjectViewerController implements SwingProjectViewer {

    /**
     * The unique instance of {@link SwingProjectViewerController}.
     */
    private volatile static SwingProjectViewerController uniqueInstance = null;
    /**
     * The current {@link Project}.
     */
    private Project project = null;

    /**
     * The private defoult constructor.
     */
    private SwingProjectViewerController() {
        super();
    }

    /**
     * Get the unique instance of {@link SwingProjectViewerController}.
     * <p/>
     * @return the unique instance of {@link SwingProjectViewerController}.
     */
    public static SwingProjectViewerController getInstance() {
        if (SwingProjectViewerController.uniqueInstance == null) {
            synchronized (SwingProjectViewerController.class) {
                SwingProjectViewerController.uniqueInstance =
                        new SwingProjectViewerController();
            }
        }
        return SwingProjectViewerController.uniqueInstance;
    }

    public Project loadProject(Project project) {
        this.project = project;
        return this.project;
    }

    public String getTitle() {
        if (this.project == null) {
            return "";
        } else {
            return this.project.getTitle();
        }
    }

    public String getAcronym() {
        if (this.project == null) {
            return "";
        } else {
            return this.project.getAcronym();
        }
    }

    public Object[][] getCriteria(CriteriaPreselection criteriaPreselection)
            throws IllegalArgumentException, IllegalAccessException {
        if (this.project == null) {
            return new Object[0][0];
        } else {
            int i = 2;
            int j = ProjectTools.getNumberOfCriteriaLines(project,
                    criteriaPreselection);
            Object[][] criteriaArray = new Object[i][j];
            ProjectTools.fillArrayWithSelectedCriteria(project,
                    criteriaPreselection, criteriaArray);
            return criteriaArray;
        }
    }
}
