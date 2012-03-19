/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.struct;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the list of projects of an ANR session.
 * <p/>
 * @author bastien
 */
public class ProjectList {

    /**
     * Field representing the {@link List} of {@link Project}.
     */
    private List<Project> projectList = new ArrayList<Project>();

    /**
     * The default constructor.
     */
    public ProjectList() {
        super();
    }

    /**
     * Get the field projectList.
     * <p/>
     * @return the {@link List} of {@link Project}.
     */
    public List<Project> getProjectList() {
        return projectList;
    }

    /**
     * Set the field projectList.
     * <p/>
     * @param projectList the {@link List} of {@link Project} to set.
     */
    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
}
