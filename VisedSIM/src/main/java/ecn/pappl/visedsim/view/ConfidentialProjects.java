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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Denis
 */
public final class ConfidentialProjects extends GUI {
    
    private JButton validateButton, cancelButton;
    private JLabel titleLabel;
    private Map<String, JCheckBox> checkboxMap;
    //Get the content of the projectsList
    private List<String> projectsList;
    
    public ConfidentialProjects(){
        super();
        build();
    }
    
    @Override
    protected void build(){
        super.build();
        setTitle(Labels.CONFIDENTIAL_PROJECTS_TITLE);
    }
    
    @Override
    protected JPanel buildContentPane(){
        panel = super.buildContentPane();
        
        titleLabel = new JLabel(Labels.CONFIDENTIAL_PROJECTS_LABEL);
        panel.add(titleLabel);
        
        JPanel middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBackground(Color.white);
        
        for(String project : projectsList){
            checkboxMap.put(project, new JCheckBox(project));
            middlePanel.add(checkboxMap.get(project));
        }
        
        panel.add(middlePanel);
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.white);
        
        validateButton = new JButton(Labels.CONFIDENTIAL_PROJECTS_VALIDATION);
        bottomPanel.add(validateButton);
        
        cancelButton = new JButton(Labels.CONFIDENTIAL_PROJECTS_CANCEL);
        bottomPanel.add(cancelButton);
        
        panel.add(bottomPanel);
        
        return panel;
    }
}
