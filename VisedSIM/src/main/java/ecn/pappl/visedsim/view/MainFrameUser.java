/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import ecn.pappl.visedsim.controller.projectviewers.SwingProjectViewerController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Denis
 */
public class MainFrameUser extends AbstractMainFrame {
    
    private JMenuBar menuBar;
    private JMenu projectMenu, criteriaMenu, helpMenu;
    private JMenuItem newListProjectItem, preselectionSavedItem, preselectionManagementItem, chooseCriteriaItem;
    private JButton chooseCriteriaButton, validateButton, searchButton;
    private JTextField searchProjectField;
    private final int TEXT_FIELD_LENGTH = 10;
    private JComboBox projectsComboBox;
    private JLabel projectTitle;
    private JTable projectTable;
    private String[] projectsArray;
    
    public MainFrameUser(List<String> projectsList){
        super();
        ProjectListController plc = ProjectListController.getInstance();
        this.projectsArray = plc.getProjectsAcronyms().toArray(new String[0]);
        build();
    }
    
    @Override
    protected JPanel buildContentPane(){
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
	panelCenter.setBackground(Color.white);
        
        JPanel panel = new JPanel(new SpringLayout());
        panel.setBackground(Color.white);
        
        //Menu
        menuBar = new JMenuBar();
        projectMenu = new JMenu(Labels.MENU_PROJECT);
        
        newListProjectItem = new JMenuItem(Labels.MENU_PROJECT_NEW_LIST);
        projectMenu.add(newListProjectItem);
        
        menuBar.add(projectMenu);
        
        criteriaMenu = new JMenu(Labels.MENU_CRITERIA);
        
        preselectionSavedItem = new JMenuItem(Labels.MENU_CRITERIA_SAVE);
        criteriaMenu.add(preselectionSavedItem);
        
        preselectionManagementItem = new JMenuItem(Labels.MENU_CRITERIA_PRESELECTION_MANAGEMENT);
        criteriaMenu.add(preselectionManagementItem);
        
        chooseCriteriaItem = new JMenuItem(Labels.MENU_CRITERIA_CHOOSE);
        chooseCriteriaItem.addActionListener(new java.awt.event.ActionListener() {
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
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseCriteriaActionEvent(evt);
            }
        });
        criteriaPanel.add(chooseCriteriaButton);
        
        buttonPanel.add(criteriaPanel);
        
        JPanel chooseProjectPanel = new JPanel(new FlowLayout());
        chooseProjectPanel.setBackground(Color.white);
        
        projectsComboBox = new JComboBox(projectsArray);
        chooseProjectPanel.add(projectsComboBox);
        
        validateButton = new JButton(Labels.MAIN_FRAME_VALIDATE_PROJECT_BUTTON);
        chooseProjectPanel.add(validateButton);
        
        buttonPanel.add(chooseProjectPanel);
        
        JPanel searchProjectPanel = new JPanel(new FlowLayout());
        searchProjectPanel.setBackground(Color.white);
        
        searchProjectField = new JTextField();
        searchProjectField.setColumns(TEXT_FIELD_LENGTH);
        searchProjectPanel.add(searchProjectField);
        
        searchButton = new JButton(Labels.MAIN_FRAME_SEARCH_PROJECT_BUTTON);
        searchProjectPanel.add(searchButton);
        
        buttonPanel.add(searchProjectPanel);
        
        SpringUtilities.makeCompactGrid(buttonPanel, 1, 3, 5, 5, 5, 5);
        
        panel.add(buttonPanel);
        
        //Representation of the project
        JPanel middlePanel = new JPanel(new FlowLayout());
        
        SwingProjectViewerController projectInstance = SwingProjectViewerController.getInstance();
        
        projectTitle = new JLabel(projectInstance.getAcronym()+" : "+projectInstance.getTitle());
        middlePanel.add(projectTitle);
        
        panel.add(middlePanel);
        
        SpringUtilities.makeCompactGrid(panel, 3, 1, 5, 5, 10, 10);
        
        panelCenter.add(panel, BorderLayout.CENTER);
        
        return panelCenter;
    }
    
}
