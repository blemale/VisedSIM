/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.projectviewers;

import ecn.pappl.visedsim.Configuration;
import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.utilities.ProjectTools;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

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
            int j = 2;
            int i = ProjectTools.getNumberOfCriteriaLines(project,
                    criteriaPreselection);
            Object[][] criteriaArray = new Object[i][j];
            ProjectTools.fillArrayWithSelectedCriteria(project,
                    criteriaPreselection, criteriaArray);
            String path = Configuration.I18N_FOLDER + File.separator
                    + "Criteria";
            System.out.println(path);
            ResourceBundle bundle = ResourceBundle.getBundle(path,
                    new Locale("FR", "fr"));
            for (int index = 0; index < criteriaArray.length; index++) {
                criteriaArray[index][0] =
                        bundle.getString((String) criteriaArray[index][0]);
            }
            return criteriaArray;
        }
    }
}
