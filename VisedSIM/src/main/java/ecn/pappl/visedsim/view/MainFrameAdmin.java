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
 *
 * @author Denis
 */
public class MainFrameAdmin extends AbstractMainFrame {
    
    private JMenuBar menuBar;
    private JMenu projectMenu, criteriaMenu, helpMenu;
    private JMenuItem newListProjectItem, printProjectItem, printAllProjectItem, generateXMLItem, generateAllXMLItem, preselectionSavedItem, preselectionManagementItem, chooseCriteriaItem;
    private JButton chooseCriteriaButton, generateAllXMLButton, printAllButton, validateButton;
    private String[] projectsArray;
    
    public MainFrameAdmin(List<String> projectsList){
        super();
        this.projectsArray = projectsList.toArray(new String[0]);
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
        menuBar.setMinimumSize(new Dimension(minWidth, minBarHeight));
        projectMenu = new JMenu(Labels.MENU_PROJECT);
        
        newListProjectItem = new JMenuItem(Labels.MENU_PROJECT_NEW_LIST);
        projectMenu.add(newListProjectItem);
        
        printProjectItem = new JMenuItem(Labels.MENU_PROJECT_PRINT);
        projectMenu.add(printProjectItem);
        
        printAllProjectItem = new JMenuItem(Labels.MENU_PROJECT_PRINT_ALL);
        projectMenu.add(printAllProjectItem);
        
        generateXMLItem = new JMenuItem(Labels.MENU_PROJECT_GENERATE_XML);
        projectMenu.add(generateXMLItem);
        
        generateAllXMLItem = new JMenuItem(Labels.MENU_PROJECT_GENERATE_ALL_XML);
        projectMenu.add(generateAllXMLItem);
        
        menuBar.add(projectMenu);
        
        criteriaMenu = new JMenu(Labels.MENU_CRITERIA);
        
        preselectionSavedItem = new JMenuItem(Labels.MENU_CRITERIA_SAVE);
        preselectionSavedItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preselectionSavedItemActionEvent(evt);
            }
        });
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
        
        generateAllXMLButton = new JButton(Labels.MAIN_FRAME_GENERATE_ALL_XML_BUTTON);
        criteriaPanel.add(generateAllXMLButton);
        
        printAllButton = new JButton(Labels.MAIN_FRAME_PRINT_ALL_BUTTON);
        criteriaPanel.add(printAllButton);
        
        buttonPanel.add(criteriaPanel);
        
        JPanel chooseProjectPanel = new JPanel(new FlowLayout());
        chooseProjectPanel.setBackground(Color.white);
        
        projectsComboBox = new JComboBox(projectsArray);
        chooseProjectPanel.add(projectsComboBox);
        
        validateButton = new JButton(Labels.MAIN_FRAME_VALIDATE_PROJECT_BUTTON);
        chooseProjectPanel.add(validateButton);
        
        buttonPanel.add(chooseProjectPanel);
        
        SpringUtilities.makeCompactGrid(buttonPanel, 1, 2, 5, 5, 5, 5);
        
        panel.add(buttonPanel);
        
        //Representation of the project
        JPanel middlePanel = new JPanel(new FlowLayout());
        
        SwingProjectViewerController spvc = SwingProjectViewerController.getInstance();
        
        projectTitle = new JLabel(spvc.getAcronym()+" : "+spvc.getTitle());
        middlePanel.add(projectTitle);
        
        Object[][] tableContent = spvc.getCriteria(CriteriaPreselectionController.getInstance().getCriteriaPreselection());
        Object[] columnsName = new Object[]{"Critère", "Valeur"};
        
        projectTable = new JTable(tableContent, columnsName);
        middlePanel.add(projectTable);
        
        panel.add(middlePanel);
        
        SpringUtilities.makeCompactGrid(panel, 3, 1, 5, 5, 10, 10);
        
        panelCenter.add(panel, BorderLayout.CENTER);
        
        return panelCenter;
    }

    
}
