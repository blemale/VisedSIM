/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.struct;

import java.util.ArrayList;

/**
 *
 * @author bastien
 */
public class ProjectList {
    private ArrayList<Project> projectList;
    
    public ProjectList(){
        super();
    }

    public ArrayList<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(ArrayList<Project> projectList) {
        this.projectList = projectList;
    }
    
    
}
