/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller;

import ecn.pappl.visedsim.struct.CriteriaPreselection;
import java.util.List;

/**
 * Interface representing a loader of {@link CriteriaPreselection}.
 * 
 * @author bastien
 */
public interface CriteriaPreselectionLoader {
    /**
     * Loads a {@link CriteriaPreselection} from a file given by its name.
     * 
     * @param fileName the path of the given file.
     * @return a {@link CriteriaPreselection}.
     */
    CriteriaPreselection loadCriteriaPreselection(String fileName);
    /**
     * Gets the names of the loadable {@link CriteriaPreselection}.
     * 
     * @return the {@link List} of loadable {@link CriteriaPreselection}s names.
     */
    List<String> getLoadableCriteriaPreselectionsNames();
}
