/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author Denis
 */
public class SeekingProjects extends JDialog {
    
    private ButtonGroup buttonGroup;
    private Map<String, JRadioButton> buttonMap;
    private JLabel titleLabel;
    private JButton validateButton;
    private List<String> projectsList;
    
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
        
        return panelCenter;
    }
}
