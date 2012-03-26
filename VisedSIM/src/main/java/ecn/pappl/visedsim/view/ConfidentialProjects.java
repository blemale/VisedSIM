/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.view.choosecriteria.ChooseConflictCriteria;
import ecn.pappl.visedsim.view.fileoption.SaveFileOption;

import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import ecn.pappl.visedsim.struct.CriteriaPreselection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Let the administrator to define the projects in conflict
 *
 * @author Denis
 */
public final class ConfidentialProjects extends JDialog {

    private JButton validateButton, cancelButton;
    private JLabel titleLabel;
    private Map<String, JCheckBox> checkboxMap;
    private static final int NUMBER_OF_COLUMNS = 5;
    private JFileChooser fileChooser;
    private JTextField filePathField; //not visible
    private Map<String, Boolean> conflictMap = new HashMap<String, Boolean>();
    //Integers used in the compact grid
    private static final int PANEL_NUMBER_OF_COLUMN = 1;
    private static final int PANEL_NUMBER_OF_ROW = 3;
    private static final int GRID_INITIAL_X = 5;
    private static final int GRID_INITIAL_Y = 5;

    /**
     * Constructor of the JDialog
     *
     * @param projectsList
     */
    public ConfidentialProjects() {
        super();
        build();
    }

    /**
     * Build the JDialog
     */
    protected void build() {
        setTitle(Labels.CONFIDENTIAL_PROJECTS_TITLE);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setAlwaysOnTop(true);
        setContentPane(buildContentPane());

        //fileChooser
        fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML File", "xml");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Sauvegarder");

        filePathField = new JTextField();

        pack();
    }

    /**
     * Build the panel
     */
    protected JPanel buildContentPane() {
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        panelCenter.setBackground(Color.white);

        JPanel panel = new JPanel();
        panel.setLayout(new SpringLayout());
        panel.setBackground(Color.white);

        titleLabel = new JLabel(Labels.CONFIDENTIAL_PROJECTS_LABEL);
        panel.add(titleLabel);

        JPanel middlePanel = new JPanel(new SpringLayout());
        middlePanel.setBackground(Color.white);

        checkboxMap = new HashMap<String, JCheckBox>();

        List<String> projectsList = ProjectListController.getInstance().
                getProjectsAcronyms();

        for (String project : projectsList) {
            checkboxMap.put(project, new JCheckBox(project));
            checkboxMap.get(project).setBackground(Color.white);
            middlePanel.add(checkboxMap.get(project));
        }
        int numberOfRows = (int) ((double) projectsList.size()
                / NUMBER_OF_COLUMNS);
        int numberOfMissedCases = projectsList.size() - numberOfRows
                * NUMBER_OF_COLUMNS;
        if (numberOfMissedCases != 0) {
            numberOfRows = numberOfRows + 1;
            for (int i = 0; i < (NUMBER_OF_COLUMNS - numberOfMissedCases); i++) {
                JLabel emptyLabel = new JLabel();
                middlePanel.add(emptyLabel);
            }
        }

        SpringUtilities.makeCompactGrid(middlePanel, numberOfRows,
                NUMBER_OF_COLUMNS, GRID_INITIAL_X, GRID_INITIAL_Y, 5, 5);

        panel.add(middlePanel);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.white);

        validateButton = new JButton(Labels.CONFIDENTIAL_PROJECTS_VALIDATION);
        validateButton.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Launch the generation of the XML
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateButtonActionPerformed();
            }
        });
        bottomPanel.add(validateButton);

        cancelButton = new JButton(Labels.CONFIDENTIAL_PROJECTS_CANCEL);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Close the JDialog without saving
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed();
            }
        });
        bottomPanel.add(cancelButton);

        panel.add(bottomPanel);

        SpringUtilities.makeCompactGrid(panel, PANEL_NUMBER_OF_ROW,
                PANEL_NUMBER_OF_COLUMN, GRID_INITIAL_X, GRID_INITIAL_Y, 5, 5);

        panelCenter.add(panel, BorderLayout.CENTER);

        return panelCenter;
    }

    /**
     * Get the fileChooser
     *
     * @return the fileChooser
     */
    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    /**
     * Get the filePathField
     * <p/>
     * @return
     */
    public JTextField getFilePathField() {
        return filePathField;
    }

    /**
     * Dispose the JDialog without saving
     *
     * @param evt
     */
    private void cancelButtonActionPerformed() {
        this.dispose();
    }

    /**
     * Launch the generation of the XML
     */
    private void validateButtonActionPerformed() {
        //TODO : mettre à jour la liste des conflits
        boolean conflict = false;
        for (String project : checkboxMap.keySet()) {
            if (checkboxMap.get(project).isSelected()) {
                conflict = true;
                conflictMap.put(project, true);
            } else {
                conflictMap.put(project, false);
            }
        }
        if (conflict) {
            ChooseConflictCriteria ccc = new ChooseConflictCriteria(this);
            ccc.setVisible(true);
        } else {
            saveXML();

        }
    }

    /**
     * Let the user choose the XML name and save the projects
     */
    public void saveXML() {
        SaveFileOption saveFileOption = new SaveFileOption(this,
                Labels.CHOOSE_NEW_LIST_BUTTON);
        saveFileOption.actionPerformed(null);
        String filePath = filePathField.getText();
        filePath = addXMLExtension(filePath);
        CriteriaPreselection criteriaPreselection =
                CriteriaPreselectionController.getInstance().
                getCriteriaPreselection();
        try {
            ProjectListController.getInstance().saveProjectListWithInterrestConflicts(filePath, conflictMap,
                    criteriaPreselection);
        } catch (Exception ex) {
            Logger.getLogger(ConfidentialProjects.class.getName()).
                    log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Une erreur s'est produite pendant la sauvegarde.");
        }
        this.dispose();
    }
    
    /**
     * Add .xml at the end of the XML file
     * 
     * @param fileName
     * @return the new fileName
     */
    private String addXMLExtension(String fileName){
        if(!fileName.endsWith(".xml")){
            fileName = fileName+".xml";
        }    
        return fileName;
    } 
}
