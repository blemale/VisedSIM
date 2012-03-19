/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

/**
 * Let the administrator to define the projects in conflict
 * 
 * @author Denis
 */
public final class ConfidentialProjects extends JDialog{
    
    private JButton validateButton, cancelButton;
    private JLabel titleLabel;
    private Map<String, JCheckBox> checkboxMap;
    private static final int NUMBER_OF_COLUMNS = 5;
    private List<String> projectsList;
    
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
    public ConfidentialProjects(){
        super();
        ProjectListController plc = ProjectListController.getInstance();
        this.projectsList = plc.getProjectsAcronyms();
        build();
    }
    
    /**
     * Build the JDialog
     */
    protected void build(){
        setTitle(Labels.CONFIDENTIAL_PROJECTS_TITLE);
        setLocationRelativeTo(null);
	setResizable(false);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setAlwaysOnTop(true);
	setContentPane(buildContentPane());
	pack();
    }
    
    /**
     * Build the panel
     */
    protected JPanel buildContentPane(){
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
        
        for(String project : projectsList){
            checkboxMap.put(project, new JCheckBox(project));
            checkboxMap.get(project).setBackground(Color.white);
            middlePanel.add(checkboxMap.get(project));
        }
        int numberOfRows = (int)((double)projectsList.size()/NUMBER_OF_COLUMNS);
        int numberOfMissedCases = projectsList.size()-numberOfRows*NUMBER_OF_COLUMNS;
        if(numberOfMissedCases != 0){
            numberOfRows = numberOfRows+1;
            for (int i = 0; i < (NUMBER_OF_COLUMNS - numberOfMissedCases); i++){
                JLabel emptyLabel = new JLabel();
                middlePanel.add(emptyLabel);
            }
        }
        
        SpringUtilities.makeCompactGrid(middlePanel, numberOfRows, NUMBER_OF_COLUMNS,GRID_INITIAL_X, GRID_INITIAL_Y, 5,5);
        
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
        
        SpringUtilities.makeCompactGrid(panel, PANEL_NUMBER_OF_ROW, PANEL_NUMBER_OF_COLUMN, GRID_INITIAL_X, GRID_INITIAL_Y, 5, 5);
        
        panelCenter.add(panel, BorderLayout.CENTER);
        
        return panelCenter;
    }
    
    /**
     * Dispose the JDialog without saving
     * 
     * @param evt 
     */
    private void cancelButtonActionPerformed(){
         this.dispose();
     }
    
    /**
     * Launch the generation of the XML
     */
    private void validateButtonActionPerformed(){
        //TODO : action when the admin validate
        
    }
}
