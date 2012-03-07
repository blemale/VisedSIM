/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.criteriapreselection;

import ecn.pappl.visedsim.struct.CriteriaPreselection;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface representing a factory for {@link CriteriaPreselection}.
 * <p/>
 * @author bastien
 */
public interface CriteriaPreselectionFactory {

    /**
     * Creates a new {@link CriteriaPreselection} with all the criteria on true.
     * <p/>
     * @return a new {@link CriteriaPreselection}.
     */
    CriteriaPreselection createCriteriaPreselection() throws
            FileNotFoundException, IOException;
}
