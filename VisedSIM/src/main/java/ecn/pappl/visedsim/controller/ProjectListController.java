/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller;

import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.struct.ProjectList;
import ecn.pappl.visedsim.utilities.XMLTools;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author bastien
 */
public abstract class ProjectListController implements ProjectListLoader, ProjectListSelector {

    private ProjectList projectList = null;

    public ProjectListController() {
        super();
    }

    public ProjectList loadProjectList(String fileName) throws FileNotFoundException, IOException {
        ProjectList list = (ProjectList) XMLTools.decodeFromFile(fileName);
        this.projectList = list;
        return this.projectList;
    }

    public Project getProjectByAcronym(String acronym) {
        if (this.projectList == null) {
            return null;
        } else {
            for (Project project : this.projectList.getProjectList()) {
                if (project.getAcronym().equals(acronym)) {
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
                list.add(project.getAcronym());
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
                if(project.getAcronym().startsWith(firstLetters)){
                    list.add(project.getAcronym());
                }
            }
            Collections.sort(list);
            return list;
        }
    }
}
