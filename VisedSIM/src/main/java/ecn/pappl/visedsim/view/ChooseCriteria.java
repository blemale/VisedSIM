/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.Configuration;
import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author Denis
 */
public final class ChooseCriteria extends JDialog {
    
    private JButton validationButton, selectAllButton, resetButton, preselectionValidateButton, cancelButton;
    private JComboBox preselectionComboBox;
    private Map<String, JCheckBox> checkboxMap;
    private final int numberOfColumns = 4;
    
    private List<String> criteriaList;
    private Object[] preselectionArray;
    
    public ChooseCriteria(List<String> criteriaList, List<String> preselectionList){
        super();
        this.criteriaList = criteriaList;
        this.preselectionArray = preselectionList.toArray();
        build();
    }
    
    /**
     * Build the frame
     */
    protected void build(){
        setTitle(Labels.CHOOSE_CRITERIA_TITLE);
	setLocationRelativeTo(null);
	setResizable(false);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
	setContentPane(buildContentPane());
	pack();
    }

    /**
     * Build the panel
     * 
     * @return the panel 
     */
    protected JPanel buildContentPane(){
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
	panelCenter.setBackground(Color.white);
                
        JPanel panel = new JPanel();
        panel.setLayout(new SpringLayout());
        panel.setBackground(Color.white);
        
        //top panel
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.white);
        
        selectAllButton = new JButton(Labels.SELECT_ALL_BUTTON);
        selectAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllButtonActionPerformed(evt);
            }
        });
        topPanel.add(selectAllButton);
        
        resetButton = new JButton(Labels.RESET_BUTTON);
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        topPanel.add(resetButton);
        
        preselectionComboBox = new JComboBox(preselectionArray);
        topPanel.add(preselectionComboBox);
        
        preselectionValidateButton = new JButton(Labels.PRESELECTION_VALIDATION_BUTTON);
        preselectionValidateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preselectionValidateButtonActionPerformed(evt);
            }
        });
        topPanel.add(preselectionValidateButton);
        
        panel.add(topPanel);
        
        GridLayout grid = new GridLayout(0, numberOfColumns);
        
        //Middle panel with the checkbox
        JPanel middlePanel = new JPanel(new SpringLayout());
        middlePanel.setBackground(Color.white);
        //middlePanel.setLayout(grid);
        
        checkboxMap = new HashMap<String, JCheckBox>();
        
        for(String criteria : criteriaList){
            checkboxMap.put(criteria, new JCheckBox(criteria));
            checkboxMap.get(criteria).setBackground(Color.white);
            middlePanel.add(checkboxMap.get(criteria));
        }
        int numberRows = (int)((double)criteriaList.size()/numberOfColumns);
        int numberOfMissedCases = criteriaList.size()-numberRows*numberOfColumns;
        if(numberOfMissedCases != 0){
            numberRows = numberRows+1;
            for (int i = 0; i < (numberOfColumns - numberOfMissedCases); i++){
                JLabel emptyLabel = new JLabel(String.valueOf(i));
                emptyLabel.setVisible(false);
                middlePanel.add(emptyLabel);
            }
        }
        
        SpringUtilities.makeCompactGrid(middlePanel, numberRows, numberOfColumns, 5, 5, 5, 5);
        
        panel.add(middlePanel);
        
        //Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.white);
        
        validationButton = new JButton(Labels.VALIDATION_BUTTON);
        bottomPanel.add(validationButton);
        
        cancelButton = new JButton(Labels.CANCEL_BUTTON);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(cancelButton);
        
        panel.add(bottomPanel);
        
        SpringUtilities.makeCompactGrid(panel, 3, 1, 5, 5, 10, 10);
        
        panelCenter.add(panel, BorderLayout.CENTER);
        
        return panelCenter;
    }
    
    private void selectAllButtonActionPerformed(java.awt.event.ActionEvent evt) {
        for(String criteria : criteriaList){
            checkboxMap.get(criteria).setSelected(true);
        }
    }
    
    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        for(String criteria : criteriaList){
            checkboxMap.get(criteria).setSelected(false);
        }
    }
    
     private void preselectionValidateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        //TODO récupérer la liste des critères choisis
         CriteriaPreselectionController cpc = CriteriaPreselectionController.getInstance();
         //String criteriaPath = Configuration.CRITERIA_PRESELECTION_FOLDER+"/"
         List<String> listPreselectedCriteria = new ArrayList<String>();
         for(String criteria : criteriaList){
             if(listPreselectedCriteria.contains(criteria)){
                checkboxMap.get(criteria).setSelected(true);
             }
             else {
                 checkboxMap.get(criteria).setSelected(false);
             }
        }
    }
     
     private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt){
         this.dispose();
     }
     
     private void validateButtonActionPerformed(java.awt.event.ActionEvent evt){
         
     }
}
