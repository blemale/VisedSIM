/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.projectlist;

import ecn.pappl.visedsim.struct.ProjectList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
    ProjectList loadProjectList(String fileName) throws FileNotFoundException, IOException;
    
        /**
     * Gets the names of the loadable {@link ProjectList}.
     * 
     * @return the {@link List} of loadable {@link ProjectList}s names.
     */
    List<String> getLoadableProjectListsNames();
}
