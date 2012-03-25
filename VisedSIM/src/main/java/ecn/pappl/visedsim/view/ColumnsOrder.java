/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.Configuration;
import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import ecn.pappl.visedsim.controller.projectviewers.SwingProjectViewerController;
import ecn.pappl.visedsim.view.mainframe.MainFrameAdmin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
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

    private JLabel columnsOrderLabel, firstRowLabel;
    private JTable columnsTable;
    private JTextField firstRowField,firstColumnField; 
    private DefaultTableModel tableModel;
    private JButton validateButton, cancelButton;
    private String fileName;
    private List<String> criteriaList;

    /**
     * Constructor
     */
    public ColumnsOrder(String fileName) {
        super();
        this.fileName = fileName;
        Object[][] tableContent;
        try {
            Map<String, List<Integer>> criteriaMap = ProjectListController.getInstance().getDefaultColumnsOrder();
            tableContent = new String[criteriaMap.keySet().size()][2];
            String path = Configuration.I18N_FOLDER + "/Criteria";
            ResourceBundle bundle = ResourceBundle.getBundle(path,
                    Locale.getDefault());
            int i = 0;
            criteriaList = new ArrayList<String>(criteriaMap.keySet());
            for (String criteria : criteriaList) {
                tableContent[i][0] = bundle.getString(criteria);
                for (Integer integer : criteriaMap.get(criteria)) {
                    if (tableContent[i][1] != null) {
                        tableContent[i][1] = tableContent[i][1] + ", " + String.valueOf(integer);
                    } else {
                        tableContent[i][1] = String.valueOf(integer);
                    }
                }
                i++;
            }

            Object[] columnsName = new Object[]{"Critère", "Numéro colonne"};
            tableModel = new DefaultTableModel(tableContent, columnsName) {

                @Override
                public boolean isCellEditable(int iRowIndex, int iColumnIndex) {
                    return true;
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
        setMinimumSize(new Dimension(300, 300));
        setResizable(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
        panelCenter.setLayout(new SpringLayout());
        panelCenter.setBackground(Color.white);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);

        columnsOrderLabel = new JLabel(Labels.COLUMNS_ORDER_TITLE);
        panel.add(columnsOrderLabel, BorderLayout.PAGE_START);
        
        JPanel firstExcelCell = new JPanel(new SpringLayout());
        firstExcelCell.setBackground(Color.white);
        
        firstRowLabel = new JLabel(Labels.COLUMNS_ORDER_FIRST_CELL);
        firstExcelCell.add(firstRowLabel);
        
        firstRowField = new JTextField(4);
        firstExcelCell.add(firstRowField);
        
        firstColumnField = new JTextField(4);
        firstExcelCell.add(firstColumnField);
        
        SpringUtilities.makeCompactGrid(firstExcelCell, 1, 3, 5, 5, 5,5);
        panel.add(firstExcelCell);

        columnsTable = new JTable(tableModel);
        panel.add(columnsTable, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new SpringLayout());
        buttonPanel.setBackground(Color.white);

        validateButton = new JButton(Labels.COLUMNS_ORDER_VALIDATE);
        validateButton.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Load the XML or Excel file
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateButtonActionEvent();
            }
        });
        buttonPanel.add(validateButton);

        cancelButton = new JButton(Labels.COLUMNS_ORDER_CANCEL);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {

            /**
             * Launch the FileLoader
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionEvent();
            }
        });
        buttonPanel.add(cancelButton);

        SpringUtilities.makeCompactGrid(buttonPanel, 1, 2, 5, 5, 10, 10);
        
        JScrollPane scrollpane = new JScrollPane(panel);
        panelCenter.add(scrollpane);
        
        panelCenter.add(buttonPanel);

        SpringUtilities.makeCompactGrid(panelCenter, 2, 1, 5, 5, 15, 5);

        //panelCenter.add(scrollpane, BorderLayout.CENTER);

        return panelCenter;
    }

    /**
     * Launch the main frame
     */
    private void validateButtonActionEvent() {
        try {
            ProjectListController plc = ProjectListController.getInstance();
            Map<String, List<Integer>> columsOrder = readTable();
            plc.loadExcelProjectList(fileName, columsOrder, 3,
                    0);
            SwingProjectViewerController swingProjectViewerController =
                    SwingProjectViewerController.getInstance();
            swingProjectViewerController.loadProject(plc.getFirstProject());

            MainFrameAdmin mainFrameAdmin = new MainFrameAdmin();
            mainFrameAdmin.setVisible(true);
            this.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the values in the tables
     * 
     * @return 
     */
    private Map<String, List<Integer>> readTable() {
        Map<String, List<Integer>> columsOrder = new HashMap<String, List<Integer>>();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String s = (String) tableModel.getValueAt(i, 1);
            String[] columnList = s.split(",");
            List<Integer> numbersList = new LinkedList<Integer>();

            for (String columnNumber : columnList) {
                int parseNumber = Integer.parseInt(columnNumber.trim());
                numbersList.add(parseNumber);
            }

            columsOrder.put(criteriaList.get(i), numbersList);
        }

        return columsOrder;
    }

    /**
     * Launch the fileLoader
     */
    private void cancelButtonActionEvent() {
        FileLoader fileLoader = new FileLoader();
        fileLoader.getFilePathField().setText(fileName);
        fileLoader.setVisible(true);
        this.dispose();
    }
}
