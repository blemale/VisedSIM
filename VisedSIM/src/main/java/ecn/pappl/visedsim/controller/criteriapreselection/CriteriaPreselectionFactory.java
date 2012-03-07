/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.criteriapreselection;

import ecn.pappl.visedsim.struct.CriteriaPreselection;

/**
 * Interface representing a factory for {@link CriteriaPreselection}.
 * 
 * @author bastien
 */
public interface CriteriaPreselectionFactory {
   /**
    * Creates a new {@link CriteriaPreselection} with all the criteria on true.
    * 
    * @return a new {@link CriteriaPreselection}. 
    */ 
   CriteriaPreselection createCriteriaPreselection(); 
}
