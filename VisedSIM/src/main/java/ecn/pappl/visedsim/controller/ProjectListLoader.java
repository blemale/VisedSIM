/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller;

import ecn.pappl.visedsim.struct.ProjectList;

/**
 * Interface representing a loader of {@link ProjectList}.
 * 
 * @author bastien
 */
public interface ProjectListLoader {
    /**
     * Load a {@link ProjectList} from a file given by is name.
     * 
     * @param fileName name of the given file to load.
     * @return a {@link ProjectList}.
     */
    ProjectList loadProjectList(String fileName);
}
