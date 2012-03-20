/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import ecn.pappl.visedsim.controller.projectviewers.SwingProjectViewerController;
import ecn.pappl.visedsim.struct.Project;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * The main frame of the program for a simple user
 *
 * @author Denis
 */
public class MainFrameUser extends AbstractMainFrame {

    private static final int TEXT_FIELD_LENGTH = 10;
    private JMenuBar menuBar;
    private JMenu projectMenu, criteriaMenu, helpMenu;
    private JMenuItem newListProjectItem, preselectionSavedItem, preselectionManagementItem, chooseCriteriaItem;
    private JButton chooseCriteriaButton, validateButton, searchButton;
    private JTextField searchProjectField;
    
    //Integers used in the compact grids
    private static final int BUTTONPANEL_NUMBER_OF_COLUMN = 3;
    private static final int BUTTONPANEL_NUMBER_OF_ROW = 1;

    /**
     * The constructor of the main frame
     */
    public MainFrameUser() {
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

    /**
     * Launch the given project or the SeekingProject if there are several
     * projects with the same first letters in their acronyme
     *
     * @param evt
     */
    protected void searchButtonActionEvent(java.awt.event.ActionEvent evt) {
        ProjectListController plc = ProjectListController.getInstance();
        String fileName = searchProjectField.getText();

        if (plc.getProjectsAcronymsByFirstLetters(fileName).
                isEmpty()) {
            JOptionPane.showMessageDialog(this, Labels.SEEKING_PROJECTS_POPUP);
        } else if (plc.getProjectsAcronymsByFirstLetters(fileName).size() == 1) {
            Project project = plc.getProjectByAcronym(fileName);
            if (project != null) {
                SwingProjectViewerController.getInstance().loadProject(project);
                this.updateProjectView();
            }
        } else {
            SeekingProjects sp = new SeekingProjects(this, fileName);
            sp.setVisible(true);
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
             * Launch the FileLoader to let the user choosing a new XML file
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newListProjectItemActionButton(evt);
            }
        });
        projectMenu.add(newListProjectItem);
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

        chooseCriteriaButton = new JButton(
                Labels.MAIN_FRAME_CHOOSE_CRITERIA_BUTTON);
        chooseCriteriaButton.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Open the ChooseCriteria to choose the criteria selection
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseCriteriaActionEvent(evt);
            }
        });
        criteriaPanel.add(chooseCriteriaButton);

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

        JPanel searchProjectPanel = new JPanel(new FlowLayout());
        searchProjectPanel.setBackground(Color.white);

        searchProjectField = new JTextField();
        searchProjectField.setColumns(TEXT_FIELD_LENGTH);
        searchProjectPanel.add(searchProjectField);

        searchButton = new JButton(Labels.MAIN_FRAME_SEARCH_PROJECT_BUTTON);
        searchButton.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Launch the given project or the SeekingProject if there are
             * several projects with the same first letters in their acronyme
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionEvent(evt);
            }
        });
        searchProjectPanel.add(searchButton);

        buttonPanel.add(searchProjectPanel);

        SpringUtilities.makeCompactGrid(buttonPanel, BUTTONPANEL_NUMBER_OF_ROW, BUTTONPANEL_NUMBER_OF_COLUMN, GRID_INITIAL_X, GRID_INITIAL_Y, 5, 5);

        return buttonPanel;
    }
}
