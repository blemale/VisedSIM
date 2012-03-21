package ecn.pappl.visedsim.view.fileoption;

import ecn.pappl.visedsim.view.FileLoader;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

/**
 * Parameter for the filechooser of the input file
 * 
 * @author Denis Delsol
 * 
 */
public class ChooseFileOption extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FileLoader fenetre;

	/**
	 * Constructor
	 * 
	 * @param fenetre
	 * @param message
	 */
	public ChooseFileOption(FileLoader fenetre, String message) {
		super(message);
		this.fenetre = fenetre;
	}

	/**
	 * Action realized when the user click on the button "Choisir..."
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
