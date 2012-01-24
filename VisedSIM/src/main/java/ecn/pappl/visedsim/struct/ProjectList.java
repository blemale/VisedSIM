/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.struct;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bastien
 */
public class ProjectList {
    private List<Project> projectList = new ArrayList<Project>();
    
    public ProjectList(){
        super();
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
    
    
}
