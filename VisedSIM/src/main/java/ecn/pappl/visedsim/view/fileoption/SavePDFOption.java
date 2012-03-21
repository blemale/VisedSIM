/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view.fileoption;

import ecn.pappl.visedsim.view.ConfidentialProjects;
import ecn.pappl.visedsim.view.MainFrameAdmin;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

/**
 *
 * @author Denis
 */
public class SavePDFOption extends AbstractAction {
    
    private MainFrameAdmin fenetre;

    /**
     * Constructor
     *
     * @param fenetre
     * @param message
     */
    public SavePDFOption(MainFrameAdmin fenetre, String message) {
        super(message);
        this.fenetre = fenetre;
    }

    public void actionPerformed(ActionEvent e) {
        fenetre.getFileChooser().setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fenetre.getFileChooser().showOpenDialog(fenetre);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fenetre.getFileChooser().getSelectedFile();
            fenetre.getFilePathField().setText(file.getAbsolutePath());
        }
    }
    
}
