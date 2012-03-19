/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.criteriapreselection;

import ecn.pappl.visedsim.struct.CriteriaPreselection;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface representing an initializer of {@link CriteriaPreselection}.
 *
 * @author bastien
 */
public interface CriteriaPreselectionInitializer {

    /**
     * Initialize the current {@link CriteriaPreselection}.
     * @return the initialized current given {@link CriteriaPreselection}.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    CriteriaPreselection initCriteriaPreselection() throws
            FileNotFoundException, IOException;
}
