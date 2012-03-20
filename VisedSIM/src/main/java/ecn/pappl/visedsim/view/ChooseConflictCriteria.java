/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;

/**
 *
 * @author Denis
 */
public final class ChooseConflictCriteria extends AbstractChooseCriteria {
    
    private ConfidentialProjects confidentialProjects;
    
    /**
     * Constructor of the JDialog
     *
     * @param mainFrame
     */
    public ChooseConflictCriteria(ConfidentialProjects confidentialProjects) {
        super();
        this.confidentialProjects = confidentialProjects;
        build();
    }
    
    @Override
    protected void validateButtonActionPerformed(){
        super.validateButtonActionPerformed();
        confidentialProjects.saveXML();
        this.dispose();
    }
    
}
