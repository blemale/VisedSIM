/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

/**
 * A JDialog with the summary of a project
 * 
 * @author Denis
 */
public class SummaryPopup extends JDialog {
    
    private JLabel titlePanel;
    private JTextArea textArea;
    private String title;
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
    public SummaryPopup(String title, String summary){
        super();
        this.title = title;
        this.summary = summary;
        build();
    }
    
    /**
     * Build the JDialog
     */
    private void build(){
        setTitle(this.title);
        setLocationRelativeTo(null);
        setResizable(true);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200));
        setContentPane(buildContentPane());
        pack();
    }
    
    /**
     * Build the panel
     * textArea
     * @return the main panel 
     */
    private JPanel buildContentPane(){
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
	panelCenter.setBackground(Color.white);
        
        JPanel panel = new JPanel(new SpringLayout());
        panel.setBackground(Color.white);
        
        titlePanel = new JLabel(this.title);
        panel.add(titlePanel);
        
        textArea = new JTextArea(this.summary);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);
        
        SpringUtilities.makeCompactGrid(panel, PANEL_NUMBER_OF_ROW, PANEL_NUMBER_OF_COLUMN, GRID_INITIAL_X, GRID_INITIAL_Y, 5, 5);
        
        panelCenter.add(panel);
        
        return panelCenter;
    }
}
