/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

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
        this.setVisible(false);
        confidentialProjects.saveXML();
        this.dispose();
    }
    
}
