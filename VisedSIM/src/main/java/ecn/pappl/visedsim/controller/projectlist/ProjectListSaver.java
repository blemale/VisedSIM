/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.projectlist;

import ecn.pappl.visedsim.struct.CriteriaPreselection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

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

    /**
     * Saves the current {@link ProjectList} at the given path and considering
     * conflicts of interrest.
     * <p/>
     * @param fileName the path where to save the current {@link CriteriaPreselection}.
     * @param conflicts a {@link Map} which represents the conflicts of
     * interrest. The keys are the acronyms of projects, and the value is true
     * if there is a conflict and false else.
     * @param conflictCriteria the {@link CriteriaPreselection} to apply for
     * project with conflict.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    void saveProjectListWithInterrestConflicts(String fileName,
            Map<String, Boolean> conflicts,
            CriteriaPreselection conflictCriteria) throws
            FileNotFoundException,
            IOException, IllegalArgumentException, IllegalAccessException;
;
}
