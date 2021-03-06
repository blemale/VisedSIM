/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view.choosecriteria;

import ecn.pappl.visedsim.view.choosecriteria.AbstractChooseCriteria;
import ecn.pappl.visedsim.view.mainframe.AbstractMainFrame;

/**
 *
 * @author Denis
 */
public final class ChooseProjectCriteria extends AbstractChooseCriteria {
    
    private AbstractMainFrame mainFrame;
    
    /**
     * Constructor of the JDialog
     *
     * @param mainFrame
     */
    public ChooseProjectCriteria(AbstractMainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        build();
    }
    
    @Override
    protected void validateButtonActionPerformed(){
        super.validateButtonActionPerformed();
        this.mainFrame.updateProjectView();
        this.dispose();
    }
    
}
