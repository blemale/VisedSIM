/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import ecn.pappl.visedsim.controller.projectviewers.SwingProjectViewerController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

/**
 * Let the user seeking a project
 * 
 * @author Denis
 */
public class SeekingProjects extends JDialog {
    
    private ButtonGroup buttonGroup;
    private Map<String, JRadioButton> buttonMap;
    private JLabel titleLabel;
    private JButton validateButton;
    private List<String> projectsList;
    
    /**
     * 
     * @param acronyme 
     */
    public SeekingProjects(String acronyme){
        super();
        ProjectListController plc = ProjectListController.getInstance();
        projectsList = plc.getProjectsAcronymsByFirstLetters(acronyme);
        build();
    }
    
    
    private void build(){
         setTitle(Labels.SEEKING_PROJECTS_TITLE);
	setLocationRelativeTo(null);
	setResizable(false);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
	setContentPane(buildContentPane());
	pack();
    }
    
    private JPanel buildContentPane(){
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
	panelCenter.setBackground(Color.white);
                
        JPanel panel = new JPanel();
        panel.setLayout(new SpringLayout());
        panel.setBackground(Color.white);
        
        titleLabel = new JLabel(Labels.SEEKING_PROJECTS_TITLE);
        panel.add(titleLabel);
        
        buttonMap = new HashMap<String, JRadioButton>();
        buttonGroup = new ButtonGroup();
        JPanel panelButton = new JPanel(new BorderLayout());
        panelButton.setBackground(Color.white);
        
        for(String project : projectsList){
            buttonMap.put(project, new JRadioButton(project));
            buttonGroup.add(buttonMap.get(project));
        }
        
        add(panelButton, buttonGroup);
        
        SpringUtilities.makeCompactGrid(panelButton, projectsList.size(), 1, 5, 5, 5, 5);
        
        panel.add(panelButton);
        
        validateButton = new JButton(Labels.SEEKING_PROJECTS_BUTTON);
        validateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateButtonActionPerformed(evt);
            }
        });
        panel.add(validateButton);
        
        SpringUtilities.makeCompactGrid(panel, 3, 1, 5,5,10,10);
        
        panelCenter.add(panel);
        
        return panelCenter;
    }
    
     private void validateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        SwingProjectViewerController spvc = SwingProjectViewerController.getInstance();
        ProjectListController plc = ProjectListController.getInstance();
        int test = 0;
        for(String project : buttonMap.keySet()){
            if(buttonMap.get(project).isSelected()){
                spvc.loadProject(plc.getProjectByAcronym(project));
                test = 1;
            }
        }
        if(test == 1){
            JOptionPane.showMessageDialog(this, Labels.SEEKING_PROJECTS_VALIDATION);
        } else {
            this.dispose();
        }
    }
}
