/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.projectlist;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface repesenting a saver for {@link ProjectList}.
 *
 * @author bastien
 */
public interface ProjectListSaver {

    /**
     * Saves the current {@link ProjectList} at the given path.
     *
     * @param fileName the path where to save the current {@link CriteriaPreselection}.
     */
    void saveProjectList(String fileName) throws FileNotFoundException,
                                                 IOException;
}
