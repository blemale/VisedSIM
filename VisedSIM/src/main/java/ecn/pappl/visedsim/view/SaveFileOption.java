/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Denis
 */
public class SaveFileOption extends AbstractAction {
    
    private ConfidentialProjects fenetre;

    /**
     * Constructor
     *
     * @param fenetre
     * @param message
     */
    public SaveFileOption(ConfidentialProjects fenetre, String message) {
        super(message);
        this.fenetre = fenetre;
    }

    /**
     * Action realized when the user has choosen a directory
     * @param e 
     */
    public void actionPerformed(ActionEvent e) {
        fenetre.getFileChooser().setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fenetre.getFileChooser().showOpenDialog(fenetre);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fenetre.getFileChooser().getSelectedFile();
            fenetre.getFilePathField().setText(file.getAbsolutePath());
        }
    }
}
