/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import ecn.pappl.visedsim.controller.projectlistprinter.PDFPrinterController;
import ecn.pappl.visedsim.controller.projectviewers.SwingProjectViewerController;
import ecn.pappl.visedsim.view.fileoption.SaveFileOption;
import ecn.pappl.visedsim.view.fileoption.SavePDFOption;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * The main frame of the program for the administrator
 *
 * @author Denis
 */
public class MainFrameAdmin extends AbstractMainFrame {

    private JMenuBar menuBar;
    private JMenu projectMenu, criteriaMenu, helpMenu;
    private JMenuItem newListProjectItem, printProjectItem, printAllProjectItem, generateXMLItem, generateAllXMLItem, preselectionSavedItem, preselectionManagementItem, chooseCriteriaItem;
    private JButton chooseCriteriaButton, generateAllXMLButton, printAllButton, validateButton;
    private JFileChooser fileChooser;
    private JTextField filePathField;
    //Integers used in the compact grids
    private static final int BUTTONPANEL_NUMBER_OF_COLUMN = 2;
    private static final int BUTTONPANEL_NUMBER_OF_ROW = 1;

    /**
     * The constructor of the main frame
     */
    public MainFrameAdmin() {
        super();
        SwingProjectViewerController spvc = SwingProjectViewerController.getInstance();
        Object[][] tableContent =
                spvc.getCriteria(CriteriaPreselectionController.getInstance().
                getCriteriaPreselection());
        Object[] columnsName = new Object[]{"Critère", "Valeur"};
        tableModel = new DefaultTableModel(tableContent, columnsName) {

            @Override
            public boolean isCellEditable(int iRowIndex, int iColumnIndex) {
                return false;
            }
        };
        build();
    }
    
    @Override
    protected void build(){
        super.build();
        fileChooser = new JFileChooser();
        
         FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML File", "xml");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Sauvegarder");

        filePathField = new JTextField();
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
     * Get the file path field
     * 
     * @return the filePathField
     */
    public JTextField getFilePathField(){
        return filePathField;
    }

    /**
     * Let the user select the projects with conflicts
     */
    private void generateAllXMLActionEvent() {
        ConfidentialProjects confidentialProjects = new ConfidentialProjects();
        confidentialProjects.setVisible(true);
    }

    /**
     * Let the user select the path to save the pdf of the projects
     *
     * @return
     */
    private void printAllProjectsActionEvent() {
        filePathField.setText(null);
        SavePDFOption savePDFOption = new SavePDFOption(this,Labels.CHOOSE_NEW_LIST_BUTTON);
        savePDFOption.actionPerformed(null);
        
        if(filePathField.getText() != null){
            PDFPrinterController printerController = new PDFPrinterController(filePathField.getText());
        }
    }

    @Override
    protected JMenuBar buildMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setMinimumSize(new Dimension(MIN_WIDTH, MIN_BAR_HEIGHT));
        projectMenu = new JMenu(Labels.MENU_PROJECT);

        newListProjectItem = new JMenuItem(Labels.MENU_PROJECT_NEW_LIST);
        newListProjectItem.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Launch the FileLoader to let the user choosing a new Excel file
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newListProjectItemActionButton(evt);
            }
        });
        projectMenu.add(newListProjectItem);

        //printProjectItem = new JMenuItem(Labels.MENU_PROJECT_PRINT);
        //projectMenu.add(printProjectItem);

        printAllProjectItem = new JMenuItem(Labels.MENU_PROJECT_PRINT_ALL);
        printAllProjectItem.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Open the ChooseCriteria to choose the criteria selection
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printAllProjectsActionEvent();
            }
        });
        projectMenu.add(printAllProjectItem);

        //generateXMLItem = new JMenuItem(Labels.MENU_PROJECT_GENERATE_XML);
        //projectMenu.add(generateXMLItem);

        generateAllXMLItem = new JMenuItem(Labels.MENU_PROJECT_GENERATE_ALL_XML);
        generateAllXMLItem.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Open the ChooseCriteria to choose the criteria selection
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateAllXMLActionEvent();
            }
        });
        projectMenu.add(generateAllXMLItem);

        menuBar.add(projectMenu);

        criteriaMenu = new JMenu(Labels.MENU_CRITERIA);

        preselectionSavedItem = new JMenuItem(Labels.MENU_CRITERIA_SAVE);
        preselectionSavedItem.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Launch the PreselectionSaving to let the user saving the current
             * preselection
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preselectionSavedItemActionEvent(evt);
            }
        });
        criteriaMenu.add(preselectionSavedItem);

        preselectionManagementItem = new JMenuItem(Labels.MENU_CRITERIA_PRESELECTION_MANAGEMENT);
        preselectionManagementItem.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Launch the CriteriaManagement to let the user delete a
             * preselection
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preselectionManagementItemActionButton(evt);
            }
        });
        criteriaMenu.add(preselectionManagementItem);

        chooseCriteriaItem = new JMenuItem(Labels.MENU_CRITERIA_CHOOSE);
        chooseCriteriaItem.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Open the ChooseCriteria to choose the criteria selection
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseCriteriaActionEvent(evt);
            }
        });
        criteriaMenu.add(chooseCriteriaItem);

        menuBar.add(criteriaMenu);

        helpMenu = new JMenu(Labels.MENU_HELP);
        menuBar.add(helpMenu);

        return menuBar;
    }

    @Override
    protected JPanel buildButtonPanel() {
        JPanel buttonPanel = new JPanel(new SpringLayout());
        buttonPanel.setBackground(Color.white);

        JPanel criteriaPanel = new JPanel(new FlowLayout());
        criteriaPanel.setBackground(Color.white);

        chooseCriteriaButton = new JButton(Labels.MAIN_FRAME_CHOOSE_CRITERIA_BUTTON);
        chooseCriteriaButton.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Open the ChooseCriteria to choose the criteria selection
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseCriteriaActionEvent(evt);
            }
        });
        criteriaPanel.add(chooseCriteriaButton);

        generateAllXMLButton = new JButton(Labels.MAIN_FRAME_GENERATE_ALL_XML_BUTTON);
        generateAllXMLButton.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Open the ChooseCriteria to choose the criteria selection
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateAllXMLActionEvent();
            }
        });
        criteriaPanel.add(generateAllXMLButton);

        printAllButton = new JButton(Labels.MAIN_FRAME_PRINT_ALL_BUTTON);
        printAllButton.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Open the ChooseCriteria to choose the criteria selection
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printAllProjectsActionEvent();
            }
        });
        criteriaPanel.add(printAllButton);

        buttonPanel.add(criteriaPanel);

        JPanel chooseProjectPanel = new JPanel(new FlowLayout());
        chooseProjectPanel.setBackground(Color.white);

        ProjectListController plc = ProjectListController.getInstance();
        projectsComboBox = new JComboBox(plc.getProjectsAcronyms().toArray(
                new String[0]));
        chooseProjectPanel.add(projectsComboBox);

        validateButton = new JButton(Labels.MAIN_FRAME_VALIDATE_PROJECT_BUTTON);
        validateButton.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Launch the selected project
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateButtonActionEvent(evt);
            }
        });
        chooseProjectPanel.add(validateButton);

        buttonPanel.add(chooseProjectPanel);

        SpringUtilities.makeCompactGrid(buttonPanel, BUTTONPANEL_NUMBER_OF_ROW, BUTTONPANEL_NUMBER_OF_COLUMN, GRID_INITIAL_X, GRID_INITIAL_Y, 5, 5);

        return buttonPanel;
    }
}
