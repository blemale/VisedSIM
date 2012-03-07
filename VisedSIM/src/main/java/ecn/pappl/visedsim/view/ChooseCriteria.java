/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author Denis
 */
public final class ChooseCriteria extends JFrame {
    
    private JButton validationButton, selectAllButton, resetButton, preselectionValidateButton, cancelButton;
    private JComboBox preselectionComboBox;
    private Map<String, JCheckBox> checkboxMap;
    final int numberOfColumns = 4;
    
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
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        topPanel.add(selectAllButton);
        
        resetButton = new JButton(Labels.RESET_BUTTON);
        topPanel.add(resetButton);
        
        preselectionComboBox = new JComboBox(preselectionArray);
        topPanel.add(preselectionComboBox);
        
        preselectionValidateButton = new JButton(Labels.PRESELECTION_VALIDATION_BUTTON);
        topPanel.add(preselectionValidateButton);
        
        panel.add(topPanel);
        
        //Middle panel with the checkbox
        JPanel middlePanel = new JPanel(new SpringLayout());
        middlePanel.setBackground(Color.white);
        
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
            for (int i = 0; i < numberOfMissedCases; i++){
                JLabel emptyLabel = new JLabel();
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
        bottomPanel.add(cancelButton);
        
        panel.add(bottomPanel);
        
        SpringUtilities.makeCompactGrid(panel, 3, 1, 5, 5, 10, 10);
        
        panelCenter.add(panel, BorderLayout.CENTER);
        
        return panelCenter;
    }
}
