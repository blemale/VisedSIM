/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;

/**
 * A JDialog with the summary of a project
 * 
 * @author Denis
 */
public class SummaryPopup extends JDialog {
    
    private JLabel titlePanel;
    private JTextArea textArea;
    private String summary;
    
     //Integers used in the compact grid
    private static final int PANEL_NUMBER_OF_COLUMN = 1;
    private static final int PANEL_NUMBER_OF_ROW = 2;
    private static final int GRID_INITIAL_X = 5;
    private static final int GRID_INITIAL_Y = 5;
    
    /**
     * Constructor of the JDialog
     * 
     * @param summary 
     */
    public SummaryPopup(String summary){
        super();
        this.summary = summary;
        build();
    }
    
    /**
     * Build the JDialog
     */
    private void build(){
        setTitle(Labels.SUMMARY_POPUP_TITLE);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setContentPane(buildContentPane());
        pack();
    }
    
    /**
     * Build the panel
     * 
     * @return the main panel 
     */
    private JPanel buildContentPane(){
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
	panelCenter.setBackground(Color.white);
        
        JPanel panel = new JPanel(new SpringLayout());
        panel.setBackground(Color.white);
        
        titlePanel = new JLabel(Labels.SUMMARY_POPUP_LABEL);
        panel.add(titlePanel);
        
        textArea = new JTextArea(this.summary);
        panel.add(textArea);
        
        SpringUtilities.makeCompactGrid(panel, PANEL_NUMBER_OF_ROW, PANEL_NUMBER_OF_COLUMN, GRID_INITIAL_X, GRID_INITIAL_Y, 5, 5);
        
        panelCenter.add(panel);
        
        return panelCenter;
    }
}
