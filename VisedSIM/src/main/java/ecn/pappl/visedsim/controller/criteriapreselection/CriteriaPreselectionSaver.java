/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.criteriapreselection;

import ecn.pappl.visedsim.struct.CriteriaPreselection;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface representing a saver of {@link CriteriaPreselection}.
 *
 * @author bastien
 */
public interface CriteriaPreselectionSaver {

    /**
     * Saves the current {@link CriteriaPreselection} at the given path.
     *
     * @param fileName the path where to save the current {@link CriteriaPreselection}.
     */
    void saveCriteriaPreselection(String fileName) throws FileNotFoundException, IOException;
}
