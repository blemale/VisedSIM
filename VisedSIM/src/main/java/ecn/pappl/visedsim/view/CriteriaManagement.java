/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

/**
 * Let the user to delete some criteria preselections
 * 
 * @author Denis
 */
public class CriteriaManagement extends JDialog {
    
    private Map<String, JCheckBox> preselectionMap;
    private JButton backButton, deleteButton;
    private JLabel titleLabel;
    private List<String> preselectionList;
    //Integers used in the compact grid
    private static final int PANEL_NUMBER_OF_COLUMN = 1;
    private static final int PANEL_NUMBER_OF_ROW = 3;
    private static final int GRID_INITIAL_X = 5;
    private static final int GRID_INITIAL_Y = 5;
    
    /**
     * The constructor of the JDialog
     * 
     * @param preselectionList 
     */
    public CriteriaManagement(){
        super();
        CriteriaPreselectionController cpc = CriteriaPreselectionController.getInstance();
        this.preselectionList = cpc.getLoadableCriteriaPreselectionsNames();
        build();
    }
    
    /**
     * Build the JDialog
     */
    private void build(){
        setTitle(Labels.CRITERIA_MANAGEMENT_TITLE);
	setLocationRelativeTo(null);
	setResizable(false);
        setModal(true);
        setAlwaysOnTop(true);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setContentPane(buildContentPane());
	pack();
    }
    
    /**
     * Build the main panel
     * 
     * @return 
     */
    private JPanel buildContentPane(){
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
	panelCenter.setBackground(Color.white);
        
        JPanel panel = new JPanel(new SpringLayout());
        panel.setBackground(Color.white);
        
        titleLabel = new JLabel(Labels.CRITERIA_MANAGEMENT_LABEL);
        panel.add(titleLabel);
        
        JPanel middlePanel = new JPanel(new SpringLayout());
        middlePanel.setBackground(Color.white);
        
        preselectionMap = new HashMap<String, JCheckBox>();
        
        for(String preselection : preselectionList){
            preselectionMap.put(preselection, new JCheckBox(preselection));
            preselectionMap.get(preselection).setBackground(Color.white);
            middlePanel.add(preselectionMap.get(preselection));
        }
        
        SpringUtilities.makeCompactGrid(middlePanel, preselectionList.size(),1, GRID_INITIAL_X, GRID_INITIAL_Y, 5, 5);
        
        panel.add(middlePanel);
        
        JPanel buttonPanel = new JPanel(new SpringLayout());
        buttonPanel.setBackground(Color.white);
        
        backButton = new JButton(Labels.CRITERIA_MANAGEMENT_BACK);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            /**
             * Close the JDialog without saving
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionEvent();
            }
        });
        buttonPanel.add(backButton);
        
        deleteButton = new JButton(Labels.CRITERIA_MANAGEMENT_DELETE);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            /**
             * Delete the selected preselection and close the JDialog
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionEvent();
            }
        });
        buttonPanel.add(deleteButton);
        
        SpringUtilities.makeCompactGrid(buttonPanel, 1, 2, GRID_INITIAL_X, GRID_INITIAL_Y, 10, 10);
        
        panel.add(buttonPanel);
        
        SpringUtilities.makeCompactGrid(panel, PANEL_NUMBER_OF_ROW, PANEL_NUMBER_OF_COLUMN, GRID_INITIAL_X, GRID_INITIAL_Y, 10, 10);
        
        panelCenter.add(panel);
        
        return panelCenter;
    }
    
    /**
     * Delete the selected checkboxes
     */
    private void deleteActionEvent(){
        int rs;
        rs = JOptionPane.showConfirmDialog(this, Labels.CRITERIA_MANAGEMENT_DIALOG, null, JOptionPane.YES_NO_OPTION);
        if(rs == JOptionPane.YES_OPTION){
            CriteriaPreselectionController cpc = CriteriaPreselectionController.getInstance();
            for (String preselection : preselectionList){
                if (preselectionMap.get(preselection).isSelected()){
                    cpc.deleteCriteriaPreselection(preselection);
                }
            }
            this.dispose();
        }
    }
    
    /**
     * Close the JDialog without saving
     */
    private void backActionEvent(){
        this.dispose();
    }
}
