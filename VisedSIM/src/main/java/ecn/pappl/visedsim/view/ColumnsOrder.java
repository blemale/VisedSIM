/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.Configuration;
import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import ecn.pappl.visedsim.controller.projectviewers.SwingProjectViewerController;
import ecn.pappl.visedsim.utilities.XMLPersistanceTools;
import ecn.pappl.visedsim.view.mainframe.MainFrameAdmin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
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
    private JButton validateButton, cancelButton;
    private String fileName;

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

        panel.add(buttonPanel);

        panelCenter.add(panel, BorderLayout.CENTER);

        return panelCenter;
    }

    /**
     * Launch the main frame
     */
    private void validateButtonActionEvent() {
        try {
            ProjectListController plc = ProjectListController.getInstance();
            InputStream is = getClass().getClassLoader().getResourceAsStream(
                    Configuration.COLUMNS_ORDER_FILE_PATH);
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
    
    private Map<String, List<Integer>> readTable(){
        Map<String, List<Integer>> columsOrder = new HashMap<String, List<Integer>>();
        
        for(int i = 0; i< tableModel.getRowCount(); i++){
            String s = (String) tableModel.getValueAt(i, 2);
            String[] columnList = s.split(",");
            List<Integer> numbersList = new LinkedList<Integer>();
            
            for(String columnNumber : columnList){
                numbersList.add(Integer.parseInt(columnNumber));
            }
            
            columsOrder.put((String)tableModel.getValueAt(i, 1), numbersList);
        }
        
        return columsOrder;
    }
    
    /**
     * Launch the fileLoader
     */
    private void cancelButtonActionEvent(){
        FileLoader fileLoader = new FileLoader();
        fileLoader.getFilePathField().setText(fileName);
        fileLoader.setVisible(true);
        this.dispose();
    }
}
