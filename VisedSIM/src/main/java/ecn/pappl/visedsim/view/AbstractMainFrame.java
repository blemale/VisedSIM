/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.Configuration;
import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Denis
 */
public abstract class AbstractMainFrame extends JFrame {

    protected void build() {
        setTitle(Labels.MAIN_FRAME_TITLE);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildContentPane());
        pack();
    }

    protected abstract JPanel buildContentPane();

    /**
     * Allow the user to modify criteria
     * <p/>
     * @param evt
     */
    protected void chooseCriteriaActionEvent(java.awt.event.ActionEvent evt) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                ChooseCriteria chooseCriteria = new ChooseCriteria();
                chooseCriteria.setVisible(true);

            }
        });
    }

    
    protected void preselectionSavedItemActionEvent(java.awt.event.ActionEvent evt){
        PreselectionSaving ps = new PreselectionSaving();
    }

}
