/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author Denis
 */
public final class ChooseCriteria extends GUI {
    
    private JButton validationButton, selectAllButton, resetButton, preselectionValidateButton, cancelButton;
    private JComboBox preselectionComboBox;
    private Map<String, JCheckBox> checkboxMap;
    
    //TODO Get the content of those two lists
    private List<String> criteriaList;
    private String[] preselectionArray;
    
    public ChooseCriteria(){
        super();
        build();
    }
    
    @Override
    protected void build(){
        super.build();
        setTitle(Labels.CHOOSE_CRITERIA_TITLE);
    }

    @Override
    protected JPanel buildContentPane(){
        panel = super.buildContentPane();
        
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
        JPanel middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBackground(Color.white);
        
        for(String criteria : criteriaList){
            checkboxMap.put(criteria, new JCheckBox(criteria));
            middlePanel.add(checkboxMap.get(criteria));
        }
        
        panel.add(middlePanel);
        
        //Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.white);
        
        validationButton = new JButton(Labels.VALIDATION_BUTTON);
        bottomPanel.add(validationButton);
        
        cancelButton = new JButton(Labels.CANCEL_BUTTON);
        bottomPanel.add(cancelButton);
        
        panel.add(bottomPanel);
        
        return panel;
    }
}
