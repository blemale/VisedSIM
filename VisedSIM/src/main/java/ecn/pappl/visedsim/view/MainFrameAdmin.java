/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import ecn.pappl.visedsim.controller.projectviewers.SwingProjectViewerController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.*;

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
    private String[] projectsArray;
    
    //Integers used in the compact grids
    private static final int PANEL_NUMBER_OF_COLUMN = 1;
    private static final int PANEL_NUMBER_OF_ROW = 3;
    private static final int MIDDLEPANEL_NUMBER_OF_COLUMN = 1;
    private static final int MIDDLEPANEL_NUMBER_OF_ROW = 2;
    private static final int BUTTONPANEL_NUMBER_OF_COLUMN = 3;
    private static final int BUTTONPANEL_NUMBER_OF_ROW = 1;
    private static final int GRID_INITIAL_X = 5;
    private static final int GRID_INITIAL_Y = 5;

    /**
     * The constructor of the main frame
     */
    public MainFrameAdmin(List<String> projectsList) {
        super();
        this.projectsArray = projectsList.toArray(new String[0]);
        build();
    }

    @Override
    protected JPanel buildContentPane() {
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        panelCenter.setBackground(Color.white);

        JPanel panel = new JPanel(new SpringLayout());
        panel.setBackground(Color.white);

        //Menu
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

        printProjectItem = new JMenuItem(Labels.MENU_PROJECT_PRINT);
        projectMenu.add(printProjectItem);

        printAllProjectItem = new JMenuItem(Labels.MENU_PROJECT_PRINT_ALL);
        projectMenu.add(printAllProjectItem);

        //generateXMLItem = new JMenuItem(Labels.MENU_PROJECT_GENERATE_XML);
        //projectMenu.add(generateXMLItem);

        generateAllXMLItem = new JMenuItem(Labels.MENU_PROJECT_GENERATE_ALL_XML);
        generateAllXMLButton.addActionListener(new java.awt.event.ActionListener() {

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

        panel.add(menuBar);

        //2nd line with buttons
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
        criteriaPanel.add(printAllButton);

        buttonPanel.add(criteriaPanel);

        JPanel chooseProjectPanel = new JPanel(new FlowLayout());
        chooseProjectPanel.setBackground(Color.white);

        projectsComboBox = new JComboBox(projectsArray);
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

        panel.add(buttonPanel);

        //Representation of the project
        middlePanel = new JPanel(new FlowLayout());

        SwingProjectViewerController spvc = SwingProjectViewerController.getInstance();

        projectTitle = new JLabel(spvc.getAcronym() + " : " + spvc.getTitle());
        middlePanel.add(projectTitle);

        Object[][] tableContent = spvc.getCriteria(CriteriaPreselectionController.getInstance().getCriteriaPreselection());
        Object[] columnsName = new Object[]{"Critère", "Valeur"};

        projectTable = new JTable(tableContent, columnsName);
        middlePanel.add(projectTable);
        
        SpringUtilities.makeCompactGrid(middlePanel, MIDDLEPANEL_NUMBER_OF_ROW, MIDDLEPANEL_NUMBER_OF_COLUMN, GRID_INITIAL_X,GRID_INITIAL_Y, 5, 5);

        scrollpane = new JScrollPane(middlePanel);
        panel.add(scrollpane);

        SpringUtilities.makeCompactGrid(panel, PANEL_NUMBER_OF_ROW,PANEL_NUMBER_OF_COLUMN, GRID_INITIAL_X, GRID_INITIAL_Y, 10, 10);

        panelCenter.add(panel, BorderLayout.CENTER);

        return panelCenter;
    }
    
    private void generateAllXMLActionEvent(){
        ConfidentialProjects confidentialProjects = new ConfidentialProjects();
        confidentialProjects.setVisible(true);
    }
}
