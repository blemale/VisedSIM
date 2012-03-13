/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.Configuration;
import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import ecn.pappl.visedsim.controller.projectviewers.SwingProjectViewerController;
import ecn.pappl.visedsim.struct.Project;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Denis
 */
public abstract class AbstractMainFrame extends JFrame {

    protected JComboBox projectsComboBox;
    protected JLabel projectTitle;
    protected JTable projectTable;
    protected DefaultTableModel tableModel;

    protected void build() {
        setTitle(Labels.MAIN_FRAME_TITLE);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildContentPane());
        pack();
    }

    protected abstract JPanel buildContentPane();

    public JTable getProjectTable() {
        return projectTable;
    }

    public void setProjectTable(JTable projectTable) {
        this.projectTable = projectTable;
    }

    public JLabel getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(JLabel projectTitle) {
        this.projectTitle = projectTitle;
    }

    public JComboBox getProjectsComboBox() {
        return projectsComboBox;
    }

    public void setProjectsComboBox(JComboBox projectsComboBox) {
        this.projectsComboBox = projectsComboBox;
    }

    /**
     * Allow the user to modify criteria
     * <p/>
     * @param evt
     */
    protected void chooseCriteriaActionEvent(java.awt.event.ActionEvent evt) {
        ChooseCriteria chooseCriteria = new ChooseCriteria(this);
        chooseCriteria.setVisible(true);
    }

    protected void preselectionSavedItemActionEvent(
            java.awt.event.ActionEvent evt) {
        PreselectionSaving ps = new PreselectionSaving();
        ps.setVisible(true);
    }

    protected void validateButtonActionEvent(java.awt.event.ActionEvent evt) {
        String fileName = (String)this.projectsComboBox.getSelectedItem();
        Project project = ProjectListController.getInstance().
                getProjectByAcronym(fileName);
        if (project != null) {
            SwingProjectViewerController.getInstance().loadProject(project);
            this.updateProjectView();
        }
    }

    public void updateProjectView() {
        SwingProjectViewerController spvc = SwingProjectViewerController.
                getInstance();
        this.projectTitle = new JLabel(spvc.getAcronym() + " : " + spvc.getTitle());
        Object[][] tableContent =
                spvc.getCriteria(CriteriaPreselectionController.getInstance().
                getCriteriaPreselection());
        Object[] columnsName = new Object[]{"Critère", "Valeur"};
        this.tableModel.setDataVector(tableContent, columnsName);
        this.tableModel.fireTableStructureChanged();
    }
}
