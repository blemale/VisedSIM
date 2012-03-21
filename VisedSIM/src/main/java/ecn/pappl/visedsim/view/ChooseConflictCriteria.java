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
    protected void build() {
        super.build();
        setTitle(Labels.CHOOSE_CRITERIA_CONFLICT_LABEL);
    }

    @Override
    protected void validateButtonActionPerformed() {
        super.validateButtonActionPerformed();
        confidentialProjects.saveXML();
        this.dispose();
    }
}
