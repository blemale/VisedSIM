/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.Configuration;
import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import ecn.pappl.visedsim.controller.projectviewers.SwingProjectViewerController;
import ecn.pappl.visedsim.struct.CriteriaPreselection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Denis
 */
public final class ChooseCriteria extends JDialog {

    private JButton validationButton, selectAllButton, resetButton, preselectionValidateButton, cancelButton;
    private JComboBox preselectionComboBox;
    private Map<String, JCheckBox> checkboxMap;
    private final int numberOfColumns = 3;
    private CriteriaPreselectionController criteriaPreselectionController =
            CriteriaPreselectionController.getInstance();
    private SwingProjectViewerController swingProjectViewerController = SwingProjectViewerController.getInstance();
    private AbstractMainFrame mainFrame;
    private Map<String, Boolean> criteriaMap;

    public ChooseCriteria(AbstractMainFrame mainFrame) {
        super();
        this.criteriaMap = this.criteriaPreselectionController.
                getCriteriaPreselection().
                getMap();
        this.mainFrame = mainFrame;
        build();
    }

    /**
     * Build the frame
     */
    protected void build() {
        setTitle(Labels.CHOOSE_CRITERIA_TITLE);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setAlwaysOnTop(true);
        setContentPane(buildContentPane());
        pack();
    }

    /**
     * Build the panel
     * <p/>
     * @return the panel
     */
    protected JPanel buildContentPane() {
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        panelCenter.setBackground(Color.white);

        JPanel panel = new JPanel();
        panel.setLayout(new SpringLayout());
        panel.setBackground(Color.white);

        //top panel
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.white);

        selectAllButton = new JButton(Labels.SELECT_ALL_BUTTON);
        selectAllButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllButtonActionPerformed(evt);
            }
        });
        topPanel.add(selectAllButton);

        resetButton = new JButton(Labels.RESET_BUTTON);
        resetButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        topPanel.add(resetButton);

        Object[] preselectionArray = criteriaPreselectionController.
                getLoadableCriteriaPreselectionsNames().toArray();
        preselectionComboBox = new JComboBox(preselectionArray);
        topPanel.add(preselectionComboBox);

        preselectionValidateButton = new JButton(
                Labels.PRESELECTION_VALIDATION_BUTTON);
        preselectionValidateButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preselectionValidateButtonActionPerformed(evt);
            }
        });
        topPanel.add(preselectionValidateButton);
        panel.add(topPanel);

        GridLayout grid = new GridLayout(0, numberOfColumns);

        //Middle panel with the checkbox
        JPanel middlePanel = new JPanel(new SpringLayout());
        middlePanel.setBackground(Color.white);
        //middlePanel.setLayout(grid);

        checkboxMap = new HashMap<String, JCheckBox>();
        String path = Configuration.I18N_FOLDER + File.separator
                + "Criteria";
        ResourceBundle bundle = ResourceBundle.getBundle(path,
                Locale.getDefault());
        for (String criteria : criteriaMap.keySet()) {
            checkboxMap.put(criteria, new JCheckBox(bundle.getString(criteria)));
            checkboxMap.get(criteria).setBackground(Color.white);
            if (criteriaMap.containsKey(criteria) && criteriaMap.get(criteria)) {
                checkboxMap.get(criteria).setSelected(true);
            }
            middlePanel.add(checkboxMap.get(criteria));
        }
        int numberRows = (int) ((double) criteriaMap.keySet().size()
                / numberOfColumns);
        int numberOfMissedCases = criteriaMap.keySet().size() - numberRows
                * numberOfColumns;
        if (numberOfMissedCases != 0) {
            numberRows = numberRows + 1;
            for (int i = 0; i < (numberOfColumns - numberOfMissedCases); i++) {
                JLabel emptyLabel = new JLabel(String.valueOf(i));
                emptyLabel.setVisible(false);
                middlePanel.add(emptyLabel);
            }
        }

        SpringUtilities.makeCompactGrid(middlePanel, numberRows, numberOfColumns,
                5, 5, 5, 5);

        panel.add(middlePanel);

        //Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.white);

        validationButton = new JButton(Labels.VALIDATION_BUTTON);
        validationButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(validationButton);

        cancelButton = new JButton(Labels.CANCEL_BUTTON);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(cancelButton);

        panel.add(bottomPanel);

        SpringUtilities.makeCompactGrid(panel, 3, 1, 5, 5, 10, 10);

        panelCenter.add(panel, BorderLayout.CENTER);

        return panelCenter;
    }

    private void selectAllButtonActionPerformed(java.awt.event.ActionEvent evt) {
        for (String criteria : criteriaMap.keySet()) {
            checkboxMap.get(criteria).setSelected(true);
        }
    }

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        for (String criteria : criteriaMap.keySet()) {
            checkboxMap.get(criteria).setSelected(false);
        }
    }

    private void preselectionValidateButtonActionPerformed(
            java.awt.event.ActionEvent evt) {
        String preselectionName = (String) this.preselectionComboBox.
                getSelectedItem();
        if (!preselectionName.isEmpty()) {
            try {
                CriteriaPreselection loadedCriteriaPreselection =
                        criteriaPreselectionController.loadCriteriaPreselection(
                        preselectionName);
                Map<String, Boolean> loadedCriteriaMap =
                        loadedCriteriaPreselection.getMap();
                for (String criteria : checkboxMap.keySet()) {
                    if (loadedCriteriaMap.containsKey(criteria)) {
                        checkboxMap.get(criteria).setSelected(loadedCriteriaMap.
                                get(criteria));
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ChooseCriteria.class.getName()).
                        log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this,
                        "La préselection est introuvable.");
            } catch (Exception ex) {
                Logger.getLogger(ChooseCriteria.class.getName()).
                        log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this,
                        "Une erreur est survenue lors du chargement de la préselection.");
            }
        }

    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void validateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        for (String criteria : criteriaMap.keySet()) {
            if (checkboxMap.containsKey(criteria) && checkboxMap.get(criteria).
                    isSelected()) {
                criteriaMap.put(criteria, true);
            } else {
                criteriaMap.put(criteria, false);
            }
        }
        this.mainFrame.updateProjectView();
        this.dispose();
    }
}
