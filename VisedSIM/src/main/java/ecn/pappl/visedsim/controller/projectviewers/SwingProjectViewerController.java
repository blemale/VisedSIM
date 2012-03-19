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
import java.util.*;

/**
 *
 * @author bastien
 */
public final class SwingProjectViewerController implements SwingProjectViewer {

    private static final String TITLE = "title";
    private static final String ACRONYM = "acronym";
    /**
     * The unique instance of {@link SwingProjectViewerController}.
     */
    private static volatile SwingProjectViewerController uniqueInstance = null;
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

    /**
     * Load a {@link Project} in the viewer.
     * <p/>
     * @param project the {@link Project} to load.
     * @return the current {@link Project} in the viewer.
     */
    public Project loadProject(Project project) {
        this.project = project;
        return this.project;
    }

    /**
     * @inheritDoc
     */
    public String getTitle() {
        if (this.project == null) {
            return "";
        } else {
            Map<String, List<String>> criteriaMap =
                    this.project.getCriteriaMap();
            if (criteriaMap.containsKey(TITLE)) {
                return criteriaMap.get(TITLE).get(0);
            } else {
                return "";
            }
        }
    }

    /**
     * @inheritDoc
     */
    public String getAcronym() {
        if (this.project == null) {
            return "";
        } else {
            Map<String, List<String>> criteriaMap =
                    this.project.getCriteriaMap();
            if (criteriaMap.containsKey(ACRONYM)) {
                return criteriaMap.get(ACRONYM).get(0);
            } else {
                return "";
            }
        }
    }

    /**
     * @inheritDoc
     */
    public Object[][] getCriteria(CriteriaPreselection criteriaPreselection) {
        if (this.project == null) {
            return new Object[0][0];
        } else {
            int j = 2;
            int i = ProjectTools.getNumberOfCriteriaLines(project,
                    criteriaPreselection);
            int index = 0;
            Object[][] criteriaArray = new Object[i][j];
            Map<String, List<String>> selectedCriteria = ProjectTools.
                    getSelectedCriteria(project, criteriaPreselection);
            String path = Configuration.I18N_FOLDER + "/Criteria";
            ResourceBundle bundle = ResourceBundle.getBundle(path,
                    Locale.getDefault());
            Map<String, String> criteriaNamesMap = new HashMap<String, String>();
            for (String criteria : selectedCriteria.keySet()) {
                criteriaNamesMap.put(bundle.getString(criteria), criteria);
            }
            List<String> criteriaNames = new ArrayList<String>(criteriaNamesMap.
                    keySet());
            Collections.sort(criteriaNames);
            for (String criteriaName : criteriaNames) {
                String criteria = criteriaNamesMap.get(criteriaName);
                for (String value : selectedCriteria.get(criteria)) {
                    criteriaArray[index][0] = criteriaName;
                    criteriaArray[index][1] = value;
                    index++;
                }
            }
            return criteriaArray;
        }
    }
}
