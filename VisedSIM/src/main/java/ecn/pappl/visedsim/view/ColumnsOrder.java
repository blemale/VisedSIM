/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Let the administrator change the order of column (if the Excel has been
 * changed)
 *
 * @author Denis
 */
public final class ColumnsOrder extends JDialog {

    private JLabel columnsOrderLabel;
    private JTable columnsTable;
    private DefaultTableModel tableModel;

    /**
     * Constructor
     */
    public ColumnsOrder() {
        super();
        Object[][] tableContent;
        try {
            Map<String, List<Integer>> criteriaMap = ProjectListController.getInstance().getDefaultColumnsOrder();
            tableContent = new String[criteriaMap.keySet().size()][2];
            int i = 0;
            for (String criteria : criteriaMap.keySet()) {
                tableContent[i][0] = criteria;
                for (Integer integer : criteriaMap.get(criteria)) {
                    tableContent[i][1] = tableContent[i][1] + ", " + integer;
                }
            }

            Object[] columnsName = new Object[]{"Critère", "Numéro colonne"};
            tableModel = new DefaultTableModel(tableContent, columnsName) {

                @Override
                public boolean isCellEditable(int iRowIndex, int iColumnIndex) {
                    return false;
                }
            };

        } catch (IOException ex) {
            Logger.getLogger(ColumnsOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

        build();
    }

    /**
     * Build the JDialog
     */
    private void build() {
        setTitle(Labels.FILE_LOADER_TITLE);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(buildContentPane());
        pack();
    }

    /**
     * Build the JPanel
     * 
     * @return 
     */
    private JPanel buildContentPane() {
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        panelCenter.setBackground(Color.white);

        JPanel panel = new JPanel();
        panel.setLayout(new ScrollPaneLayout());
        panel.setBackground(Color.white);

        columnsOrderLabel = new JLabel(Labels.COLUMNS_ORDER_TITLE);
        panel.add(columnsOrderLabel);

        columnsTable = new JTable(tableModel);
        panel.add(columnsTable);
        
        panelCenter.add(panel, BorderLayout.CENTER);

        return panelCenter;
    }
}
