/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view.choosecriteria;

import ecn.pappl.visedsim.view.ConfidentialProjects;
import ecn.pappl.visedsim.view.Labels;
import ecn.pappl.visedsim.view.choosecriteria.AbstractChooseCriteria;

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
        this.setVisible(false);
        confidentialProjects.saveXML();
        this.dispose();
    }
}
