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
    
    /**
     * The constructor of the JDialog
     * 
     * @param preselectionList 
     */
    public CriteriaManagement(List<String> preselectionList){
        super();
        this.preselectionList = preselectionList;
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
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        
        SpringUtilities.makeCompactGrid(middlePanel, preselectionList.size(),1, 5, 5, 5, 5);
        
        panel.add(panelCenter, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new SpringLayout());
        buttonPanel.setBackground(Color.white);
        
        backButton = new JButton(Labels.CRITERIA_MANAGEMENT_BACK);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionEvent();
            }
        });
        buttonPanel.setBackground(Color.white);
        
        deleteButton = new JButton(Labels.CRITERIA_MANAGEMENT_DELETE);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionEvent();
            }
        });
        buttonPanel.setBackground(Color.white);
        
        SpringUtilities.makeCompactGrid(buttonPanel, 1, 2, 5, 5, 10, 10);
        
        panel.add(buttonPanel);
        
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
        }
    }
    
    /**
     * Close the JDialog without saving
     */
    private void backActionEvent(){
        this.dispose();
    }
}
