/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.criteriapreselection;

import java.io.File;

/**
 * Interface representing an erasor of {@link CriteriaPreselection}.
 *
 * @author bastien
 */
public interface CriteriaPreselectionErasor {

    /**
     * Delete the given {@link File} given by its path.
     * <p/>
     * @param fileName the path of the given {@link File}
     */
    void deleteCriteriaPreselection(final String fileName);
}
