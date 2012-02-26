/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Denis
 */
public class GUI extends JFrame {
    
    protected JPanel panel;
    
    public GUI(){
        super();
    }
    
    /**
     * Build the frame
     */
    protected void build(){
        setTitle("VisedSIM");
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
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        
        return panel;
    }
}
