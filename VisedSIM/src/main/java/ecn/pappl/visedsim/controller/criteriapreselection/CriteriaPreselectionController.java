/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.criteriapreselection;

import ecn.pappl.visedsim.struct.CriteriaPreselection;
import java.util.List;

/**
 * Class representing a controller for a {@link CriteriaPreselection}
 *
 * @author bastien
 */
public class CriteriaPreselectionController implements CriteriaPreselectionFactory, CriteriaPreselectionLoader, CriteriaPreselectionSaver {

    public CriteriaPreselection createCriteriaPreselection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<String> getLoadableCriteriaPreselectionsNames() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public CriteriaPreselection loadCriteriaPreselection(String fileName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void saveCriteriaPreselection(String fileName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
