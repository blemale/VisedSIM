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
public final class ConfidentialProjects extends JDialog{
    
    private JButton validateButton, cancelButton;
    private JLabel titleLabel;
    private Map<String, JCheckBox> checkboxMap;
    private static final int NUMBER_OF_COLUMNS = 5;
    private List<String> projectsList;
    
    public ConfidentialProjects(List<String> projectsList){
        super();
        this.projectsList = projectsList;
        build();
    }
    
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
        
        SpringUtilities.makeCompactGrid(middlePanel, numberOfRows, NUMBER_OF_COLUMNS, 5,5,5,5);
        
        panel.add(middlePanel);
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.white);
        
        validateButton = new JButton(Labels.CONFIDENTIAL_PROJECTS_VALIDATION);
        bottomPanel.add(validateButton);
        
        cancelButton = new JButton(Labels.CONFIDENTIAL_PROJECTS_CANCEL);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(cancelButton);
        
        panel.add(bottomPanel);
        
        SpringUtilities.makeCompactGrid(panel, 3, 1, 5, 5, 5, 5);
        
        panelCenter.add(panel, BorderLayout.CENTER);
        
        return panelCenter;
    }
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt){
         this.dispose();
     }
}
