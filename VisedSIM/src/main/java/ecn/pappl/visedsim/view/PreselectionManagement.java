/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 *
 * @author Denis
 */
public class PreselectionManagement extends JDialog {
    
    private JLabel titleLabel;
    private Map<String, JCheckBox> checkboxMap;
    
    
    public PreselectionManagement(){
        super();
        build();
    }
    
    private void build(){
        setTitle(Labels.PRESELECTION_MANAGEMENT_TITLE);
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
