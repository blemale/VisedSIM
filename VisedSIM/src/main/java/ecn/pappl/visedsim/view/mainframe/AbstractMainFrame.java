/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view.mainframe;

import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import ecn.pappl.visedsim.controller.projectviewers.SwingProjectViewerController;
import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.view.choosecriteria.ChooseProjectCriteria;
import ecn.pappl.visedsim.view.CriteriaManagement;
import ecn.pappl.visedsim.view.FileLoader;
import ecn.pappl.visedsim.view.Labels;
import ecn.pappl.visedsim.view.PreselectionSaving;
import ecn.pappl.visedsim.view.SpringUtilities;
import ecn.pappl.visedsim.view.SummaryPopup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Content the public actions for the two main frames.
 *
 * @author Denis
 */
public abstract class AbstractMainFrame extends JFrame {

    protected JComboBox projectsComboBox;
    protected JLabel projectTitle;
    protected JTable projectTable;
    protected DefaultTableModel tableModel;
    protected JScrollPane scrollpane;
    protected JPanel middlePanel;
    private static final int MIN_HEIGHT = 300;
    protected static final int MIN_WIDTH = 580;
    protected static final int MIN_BAR_HEIGHT = 30;
    protected static final int MAX_BUTTON_PANEL_HEIGHT = 100;
    
        //Integers used in the compact grids
    private static final int PANEL_NUMBER_OF_COLUMN = 1;
    private static final int PANEL_NUMBER_OF_ROW = 3;
    protected static final int GRID_INITIAL_X = 5;
    protected static final int GRID_INITIAL_Y = 5;

    /**
     * Build the frame
     */
    protected void build() {
        setTitle(Labels.MAIN_FRAME_TITLE);
        setLocationRelativeTo(null);
        setResizable(true);
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setMaximumSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        setPreferredSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildContentPane());
        pack();
    }

    /**
     * Build the main panel
     * 
     * @return the main panel
     */
    protected JPanel buildContentPane(){
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        panelCenter.setBackground(Color.white);

        JPanel panel = new JPanel(new SpringLayout());
        panel.setBackground(Color.white);

        //Menu
        panel.add(buildMenuBar());

        //2nd line with buttons
        panel.add(buildButtonPanel());

        //Representation of the project
        middlePanel = new JPanel(new BorderLayout());

        SwingProjectViewerController spvc = SwingProjectViewerController.getInstance();

        projectTitle = new JLabel(spvc.getAcronym() + " : " + spvc.getTitle());
        middlePanel.add(projectTitle, BorderLayout.PAGE_START);

        projectTable = new JTable(tableModel);

        projectTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {

                if (arg0.getClickCount() == 2) {
                    int rowNumb = projectTable.rowAtPoint(arg0.getPoint());
                    int colNumb = projectTable.columnAtPoint(arg0.getPoint());
                    if (rowNumb != -1 && colNumb > 0) {
                        String criteria = projectTable.getValueAt(rowNumb, 0).
                                toString();
                        String value = projectTable.getValueAt(rowNumb, colNumb).
                                toString();
                        SummaryPopup popup = new SummaryPopup(criteria, value);
                        popup.setVisible(true);
                    }
                }
            }

            public void mousePressed(MouseEvent me) {
            }
            
            public void mouseReleased(MouseEvent me) {
            }

            public void mouseEntered(MouseEvent me) {
            }

            public void mouseExited(MouseEvent me) {
            }
        });
        
        middlePanel.add(projectTable, BorderLayout.CENTER);

        //SpringUtilities.makeCompactGrid(middlePanel, MIDDLEPANEL_NUMBER_OF_ROW, MIDDLEPANEL_NUMBER_OF_COLUMN, GRID_INITIAL_X,GRID_INITIAL_Y, 5, 5);

        scrollpane = new JScrollPane(middlePanel);
        panel.add(scrollpane);

        SpringUtilities.makeCompactGrid(panel, PANEL_NUMBER_OF_ROW, PANEL_NUMBER_OF_COLUMN, GRID_INITIAL_X, GRID_INITIAL_Y, 10, 10);

        panelCenter.add(panel, BorderLayout.CENTER);

        return panelCenter;
    }
    
    /**
     * Build the menu bar
     * 
     * @return the menuBar 
     */
    protected abstract JMenuBar buildMenuBar();
    
    /**
     * Build the buttons Panel used in buildContentPane
     * 
     * @return the buttonPanel
     */
    protected abstract JPanel buildButtonPanel();
        
    /**
     * Get the projectTable
     * 
     * @return the projectTable 
     */
    public JTable getProjectTable() {
        return projectTable;
    }

    /**
     * Set the projectTable
     * 
     * @param projectTable 
     */
    public void setProjectTable(JTable projectTable) {
        this.projectTable = projectTable;
    }

    /**
     * Get the projectTitle
     * 
     * @return the projectTitle 
     */
    public JLabel getProjectTitle() {
        return projectTitle;
    }

    /**
     * Set the projectTitle
     * 
     * @param projectTitle 
     */
    public void setProjectTitle(JLabel projectTitle) {
        this.projectTitle = projectTitle;
    }

    /**
     * Get the projectsComboBox
     * 
     * @return the projectsComboBox 
     */
    public JComboBox getProjectsComboBox() {
        return projectsComboBox;
    }

    /**
     * Set a projectsComboBox
     * 
     * @param projectsComboBox 
     */
    public void setProjectsComboBox(JComboBox projectsComboBox) {
        this.projectsComboBox = projectsComboBox;
    }

    /**
     * Allow the user to modify criteria
     * <p/>
     * @param evt
     */
    protected void chooseCriteriaActionEvent(java.awt.event.ActionEvent evt) {
        ChooseProjectCriteria chooseCriteria = new ChooseProjectCriteria(this);
        chooseCriteria.setVisible(true);
    }

    /**
     * Open a new JDialog to give a name to the new criteria preselection
     * 
     * @param evt 
     */
    protected void preselectionSavedItemActionEvent(
            java.awt.event.ActionEvent evt) {
        PreselectionSaving ps = new PreselectionSaving();
        ps.setVisible(true);
    }

    /**
     * Launch the project selected in the comboBox
     * 
     * @param evt 
     */
    protected void validateButtonActionEvent(java.awt.event.ActionEvent evt) {
        String fileName = (String) this.projectsComboBox.getSelectedItem();
        Project project = ProjectListController.getInstance().
                getProjectByAcronym(fileName);
        if (project != null) {
            SwingProjectViewerController.getInstance().loadProject(project);
            this.updateProjectView();
        }
    }

    /**
     * Launch the first frame of the program to load a new project list
     * 
     * @param evt 
     */
    protected void newListProjectItemActionButton(java.awt.event.ActionEvent evt) {
        FileLoader fileLoader = new FileLoader();
        fileLoader.setVisible(true);
        this.dispose();
    }

    /**
     * Launch a new JDialog to delete some preselections
     * 
     * @param evt 
     */
    protected void preselectionManagementItemActionButton(java.awt.event.ActionEvent evt) {
        CriteriaManagement preselectionManagement = new CriteriaManagement();
        preselectionManagement.setVisible(true);
    }

    /**
     * Reload the element of the project table
     */
    public void updateProjectView() {
        SwingProjectViewerController spvc = SwingProjectViewerController.getInstance();
        this.projectTitle.setText(spvc.getAcronym() + " : " + spvc.getTitle());
        Object[][] tableContent =
                spvc.getCriteria(CriteriaPreselectionController.getInstance().
                getCriteriaPreselection());
        Object[] columnsName = new Object[]{"Critère", "Valeur"};
        this.tableModel.setDataVector(tableContent, columnsName);
        this.tableModel.fireTableStructureChanged();
        this.projectTable.revalidate();
        this.scrollpane.repaint();
    }
}