/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view.choosecriteria;

import ecn.pappl.visedsim.Configuration;
import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.view.Labels;
import ecn.pappl.visedsim.view.SpringUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Let the user to change the visible criteria
 *
 * @author Denis
 */
public abstract class AbstractChooseCriteria extends JDialog {

    private JButton validationButton, selectAllButton, resetButton, preselectionValidateButton, cancelButton;
    private JComboBox preselectionComboBox;
    private JLabel titleLabel;
    private Map<String, JCheckBox> checkboxMap;
    private static final int NUMBER_OF_COLUMNS = 3;
    private CriteriaPreselectionController criteriaPreselectionController =
            CriteriaPreselectionController.getInstance();
    private Map<String, Boolean> criteriaMap;
    //Integers used in the compact grid
    private static final int PANEL_NUMBER_OF_COLUMN = 1;
    private static final int PANEL_NUMBER_OF_ROW = 4;
    private static final int GRID_INITIAL_X = 5;
    private static final int GRID_INITIAL_Y = 5;

    /**
     * Constructor of the JDialog
     *
     * @param mainFrame
     */
    public AbstractChooseCriteria() {
        super();
        this.criteriaMap = this.criteriaPreselectionController.getCriteriaPreselection().
                getMap();
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

            /**
             * Select all the criteria
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllButtonActionPerformed();
            }
        });
        topPanel.add(selectAllButton);

        resetButton = new JButton(Labels.RESET_BUTTON);
        resetButton.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Reset all selection
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed();
            }
        });
        topPanel.add(resetButton);

        Object[] preselectionArray = criteriaPreselectionController.getLoadableCriteriaPreselectionsNames().toArray();
        preselectionComboBox = new JComboBox(preselectionArray);
        topPanel.add(preselectionComboBox);

        preselectionValidateButton = new JButton(
                Labels.PRESELECTION_VALIDATION_BUTTON);
        preselectionValidateButton.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Launch the preselection
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preselectionValidateButtonActionPerformed();
            }
        });
        topPanel.add(preselectionValidateButton);
        panel.add(topPanel);

        //2nd line with a label
        titleLabel = new JLabel(Labels.CHOOSE_CRITERIA_CONFLICT_LABEL);
        panel.add(titleLabel);

        //Middle panel with the checkbox
        JPanel middlePanel = new JPanel(new SpringLayout());
        middlePanel.setBackground(Color.white);
        //middlePanel.setLayout(grid);

        checkboxMap = new HashMap<String, JCheckBox>();
        String path = Configuration.I18N_FOLDER + "/Criteria";
        ResourceBundle bundle = ResourceBundle.getBundle(path,
                Locale.getDefault());
        List<String> criteriaNames = new LinkedList<String>();
        for (String criteria : criteriaMap.keySet()) {
            criteriaNames.add(bundle.getString(criteria));
            checkboxMap.put(criteria, new JCheckBox(bundle.getString(criteria)));
            checkboxMap.get(criteria).setBackground(Color.white);
            if (criteriaMap.containsKey(criteria) && criteriaMap.get(criteria)) {
                checkboxMap.get(criteria).setSelected(true);
            }
//            middlePanel.add(checkboxMap.get(criteria));
        }
        Collections.sort(criteriaNames);
        for (String names : criteriaNames) {
            for (String criteria : checkboxMap.keySet()) {
                if (bundle.getString(criteria).equals(names)) {
                    middlePanel.add(checkboxMap.get(criteria));
                }
            }
        }
        int numberRows = (int) ((double) criteriaMap.keySet().size()
                / NUMBER_OF_COLUMNS);
        int numberOfMissedCases = criteriaMap.keySet().size() - numberRows
                * NUMBER_OF_COLUMNS;
        if (numberOfMissedCases != 0) {
            numberRows = numberRows + 1;
            for (int i = 0; i < (NUMBER_OF_COLUMNS - numberOfMissedCases); i++) {
                JLabel emptyLabel = new JLabel(String.valueOf(i));
                emptyLabel.setVisible(false);
                middlePanel.add(emptyLabel);
            }
        }

        SpringUtilities.makeCompactGrid(middlePanel, numberRows, NUMBER_OF_COLUMNS,
                GRID_INITIAL_X, GRID_INITIAL_Y, 5, 5);

        panel.add(middlePanel);

        //Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.white);

        validationButton = new JButton(Labels.VALIDATION_BUTTON);
        validationButton.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Valiate the preselection and close the JDialog
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateButtonActionPerformed();
            }
        });
        bottomPanel.add(validationButton);

        cancelButton = new JButton(Labels.CANCEL_BUTTON);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Close the JDialog without saving
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed();
            }
        });
        bottomPanel.add(cancelButton);

        panel.add(bottomPanel);

        SpringUtilities.makeCompactGrid(panel, PANEL_NUMBER_OF_ROW, PANEL_NUMBER_OF_COLUMN, GRID_INITIAL_X, GRID_INITIAL_Y, 10, 10);

        panelCenter.add(panel, BorderLayout.CENTER);

        return panelCenter;
    }

    /**
     * Select all the checkboxes
     */
    private void selectAllButtonActionPerformed() {
        for (String criteria : criteriaMap.keySet()) {
            checkboxMap.get(criteria).setSelected(true);
        }
    }

    /**
     * Reset the selection for all checkboxes
     */
    private void resetButtonActionPerformed() {
        for (String criteria : criteriaMap.keySet()) {
            checkboxMap.get(criteria).setSelected(false);
        }
    }

    /**
     * Launch the preselection of criteria
     */
    private void preselectionValidateButtonActionPerformed() {
        String preselectionName = (String) this.preselectionComboBox.getSelectedItem();
        if (!preselectionName.isEmpty()) {
            try {
                CriteriaPreselection loadedCriteriaPreselection =
                        criteriaPreselectionController.loadCriteriaPreselection(
                        preselectionName);
                Map<String, Boolean> loadedCriteriaMap =
                        loadedCriteriaPreselection.getMap();
                for (String criteria : checkboxMap.keySet()) {
                    if (loadedCriteriaMap.containsKey(criteria)) {
                        checkboxMap.get(criteria).setSelected(loadedCriteriaMap.get(criteria));
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AbstractChooseCriteria.class.getName()).
                        log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this,
                        "La préselection est introuvable.");
            } catch (Exception ex) {
                Logger.getLogger(AbstractChooseCriteria.class.getName()).
                        log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this,
                        "Une erreur est survenue lors du chargement de la préselection.");
            }
        }

    }

    /**
     * Close the JDialog without saving
     */
    private void cancelButtonActionPerformed() {
        this.dispose();
    }

    /**
     * Validate the new criteria selection
     */
    protected void validateButtonActionPerformed() {
        for (String criteria : criteriaMap.keySet()) {
            if (checkboxMap.containsKey(criteria) && checkboxMap.get(criteria).
                    isSelected()) {
                criteriaMap.put(criteria, true);
            } else {
                criteriaMap.put(criteria, false);
            }
        }
    }
}
