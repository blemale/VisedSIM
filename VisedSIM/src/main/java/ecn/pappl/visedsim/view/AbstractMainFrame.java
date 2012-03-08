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
public abstract class AbstractMainFrame extends JFrame{
    
    protected void build(){
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
     * 
     * @param evt 
     */
    protected void chooseCriteriaActionEvent(java.awt.event.ActionEvent evt) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                CriteriaPreselectionController cpc = CriteriaPreselectionController.getInstance();
                try {
                    ChooseCriteria chooseCriteria = new ChooseCriteria(new ArrayList(cpc.createCriteriaPreselection().getMap().keySet()), cpc.getLoadableCriteriaPreselectionsNames());
                    chooseCriteria.setVisible(true);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Le fichier de configuration "+Configuration.DEFAULT_CRITERIA_PRESELECTION_PATH+" est introuvable");
                    Logger.getLogger(MainFrameUser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur de lecture");
                    Logger.getLogger(MainFrameUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
