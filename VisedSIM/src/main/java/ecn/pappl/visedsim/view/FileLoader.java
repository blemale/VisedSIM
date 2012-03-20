package ecn.pappl.visedsim.view;

//import java.awt.BorderLayout;
import ecn.pappl.visedsim.Configuration;
import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import ecn.pappl.visedsim.controller.projectviewers.SwingProjectViewerController;
import ecn.pappl.visedsim.utilities.XMLPersistanceTools;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * First frame of the program
 * 
 * @author Denis
 */
public final class FileLoader extends JFrame {

    private JLabel newProjectListLabel;
    private JButton chooseNewListButton, loadNewListButton;
    private JTextField filePathField;
    private JFileChooser fileChooser;
    private static final int TEXT_COLUMN_LENGTH = 10;
    
    //Integers used in the compact grid
    private static final int PANEL_NUMBER_OF_COLUMN = 1;
    private static final int PANEL_NUMBER_OF_ROW = 3;
    private static final int GRID_INITIAL_X = 5;
    private static final int GRID_INITIAL_Y = 5;

    /**
     * Constructor of FileLoader
     */
    public FileLoader() {
        super();
        build();
    }

    /**
     * Build the frame
     */
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
                    "Fichiers XML", "xml");
            fileChooser.setFileFilter(filter);
        } else {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Fichiers Excel", new String[]{"xls", "xlsx"});
            fileChooser.setFileFilter(filter);
        }
    }

    /**
     * Build the panel
     * 
     * @return 
     */
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
            /**
             * Load the XML or Excel file
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadNewListButtonActionPerformed();
            }
        });
        panel.add(loadNewListButton);

        SpringUtilities.makeCompactGrid(panel, PANEL_NUMBER_OF_ROW, PANEL_NUMBER_OF_COLUMN, GRID_INITIAL_X, GRID_INITIAL_Y, 10, 10);

        panelCenter.add(panel, BorderLayout.CENTER);
        return panelCenter;
    }

    /**
     * Load the XML or Excel file
     */
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
        else {
            try{
                ProjectListController plc = ProjectListController.getInstance();
            InputStream is = getClass().getClassLoader().getResourceAsStream(Configuration.COLUMNS_ORDER_FILE_PATH);
            Map<String, List<Integer>> columsOrder = (Map<String, List<Integer>>)XMLPersistanceTools.decodeFromFile(is);
            plc.loadExcelProjectList(filePathField.getText(), columsOrder, 0, 3);
            MainFrameAdmin mainFrameAdmin = new MainFrameAdmin();
            mainFrameAdmin.setVisible(true);
            this.dispose();
            } catch (Exception ex){
                ex.printStackTrace();
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
