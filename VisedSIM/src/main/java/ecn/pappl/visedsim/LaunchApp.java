/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim;

import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import ecn.pappl.visedsim.view.FileLoader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author bastien
 */
public class LaunchApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        }
         
        
        try {
            CriteriaPreselectionController criteriaPreselectionController = CriteriaPreselectionController.getInstance();
            criteriaPreselectionController.initCriteriaPreselection();
            
            FileLoader fileLoader = new FileLoader();
            fileLoader.setVisible(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LaunchApp.class.getName()).log(Level.SEVERE, null,
                    ex);
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite au lancement du programme.\n"+ex.toString());
        } catch (Exception ex) {
            Logger.getLogger(LaunchApp.class.getName()).log(Level.SEVERE, null,
                    ex);
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite au lancement du programme.\n"+ex.toString());
        }
    }
}
