package ecn.pappl.visedsim.view;

//import java.awt.BorderLayout;
import ecn.pappl.visedsim.Configuration;
import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import ecn.pappl.visedsim.controller.projectviewers.SwingProjectViewerController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Denis
 */
public final class FileLoader extends JFrame {

    private JLabel newProjectListLabel;
    private JButton chooseNewListButton, loadNewListButton;
    private JTextField filePathField;
    private JFileChooser fileChooser;
    private static final int TEXT_COLUMN_LENGTH = 10;
//    private String[] projectListArray;

    /**
     * Constructor of FileLoader
     */
    public FileLoader() {
        super();
        build();
    }

    //@Override
    protected void build() {
        setTitle(Labels.FILE_LOADER_TITLE);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(buildContentPane());
        pack();

        fileChooser = new JFileChooser();

        if (!Configuration.IS_ADMIN) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "XML File", "xml");
            fileChooser.setFileFilter(filter);
        }
    }

    //@Override
    protected JPanel buildContentPane() {
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        panelCenter.setBackground(Color.white);

        JPanel panel = new JPanel();
        panel.setLayout(new SpringLayout());
        panel.setBackground(Color.white);

        //1st line
        /*
         * listProjectLabel = new JLabel(Labels.LIST_PROJECT);
         * panel.add(listProjectLabel);
         */

        newProjectListLabel = new JLabel(Labels.NEW_PROJECT_LIST);
        panel.add(newProjectListLabel);

        //2nd line
        /*
         * projectListComboBox = new JComboBox(projectListArray);
         * panel.add(projectListComboBox);
         */

        JPanel chooseFilePanel = new JPanel();
        chooseFilePanel.setLayout(new FlowLayout());
        chooseFilePanel.setBackground(Color.white);

        filePathField = new JTextField();
        filePathField.setColumns(TEXT_COLUMN_LENGTH);
        chooseFilePanel.add(filePathField);

        chooseNewListButton = new JButton(new ChooseFileOption(this,
                Labels.CHOOSE_NEW_LIST_BUTTON));
        chooseFilePanel.add(chooseNewListButton);

        panel.add(chooseFilePanel);

        //3rd line
        /*
         * loadSavedListButton = new JButton(Labels.LOAD_SAVED_LIST_BUTTON);
         * panel.add(loadSavedListButton);
         */

        loadNewListButton = new JButton(Labels.LOAD_NEW_LIST_BUTTON);
        loadNewListButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadNewListButtonActionPerformed();
            }
        });
        panel.add(loadNewListButton);

        SpringUtilities.makeCompactGrid(panel, 3, 1, 5, 5, 10, 10);

        panelCenter.add(panel, BorderLayout.CENTER);
        return panelCenter;
    }

    private void loadNewListButtonActionPerformed() {
        if (!Configuration.IS_ADMIN) {
            try {
                this.setVisible(false);
                ProjectListController projectListController =
                        ProjectListController.getInstance();
                projectListController.loadProjectList(filePathField.getText());
                SwingProjectViewerController swingProjectViewerController =
                        SwingProjectViewerController.getInstance();
                swingProjectViewerController.loadProject(projectListController.
                        getFirstProject());
                MainFrameUser mainFrameUser = new MainFrameUser();
                mainFrameUser.setVisible(true);
                this.dispose();
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Le fichier n'existe pas.");
                this.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Un problème est survenue au chargement du fichier.");
                this.setVisible(true);
            } 
        }
    }

    /**
     * get fileChooser;
     * <p/>
     * @return
     */
    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    /**
     * get filePathField
     * <p/>
     * @return
     */
    public JTextField getFilePathField() {
        return filePathField;
    }
}
