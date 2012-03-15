/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Let the user to save the current criteria preselection
 * 
 * @author Denis
 */
public class PreselectionSaving extends JDialog {
    
    private JButton saveButton;
    private JLabel titleLabel;
    private JTextField preselectionNameField;
    private static final int COLUMN_LENGHT = 10;
    
    /**
     * The constructor of the JDialog
     */
    public PreselectionSaving(){
        super();
        build();
    }
    
    /**
     * Build the JDialog
     */
    private void build(){
        setTitle(Labels.PRESELECTION_SAVING_TITLE);
	setLocationRelativeTo(null);
	setResizable(false);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
	setContentPane(buildContentPane());
	pack();
    }
    
    /**
     * Build the panel
     * 
     * @return the panel 
     */
    private JPanel buildContentPane(){
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
	panelCenter.setBackground(Color.white);
                
        JPanel panel = new JPanel();
        panel.setLayout(new SpringLayout());
        panel.setBackground(Color.white);
        
        titleLabel = new JLabel(Labels.PRESELECTION_SAVING_LABEL);
        panel.add(titleLabel);
        
        JPanel namePanel = new JPanel(new FlowLayout());
        namePanel.setBackground(Color.white);
        
        preselectionNameField = new JTextField();
        preselectionNameField.setColumns(COLUMN_LENGHT);
        
        namePanel.add(preselectionNameField);
        
        saveButton = new JButton(Labels.PRESELECTION_SAVING_BUTTON);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            /**
             * Save the criteria preselection
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed();
            }
        });
        namePanel.add(saveButton);
        
        panel.add(namePanel);
        
        SpringUtilities.makeCompactGrid(panel, 2, 1, 5, 5, 10, 10);
        
        panelCenter.add(panel, BorderLayout.CENTER);
        
        return panelCenter;
    }
    
    /**
     * Save the preselection with the name given by the user
     */
    private void saveButtonActionPerformed(){
        CriteriaPreselectionController cpc = CriteriaPreselectionController.getInstance();
        try {
            cpc.saveCriteriaPreselection(preselectionNameField.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Un problème est survenu pendant l'enregistrement !");
            Logger.getLogger(PreselectionSaving.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
     }
}
