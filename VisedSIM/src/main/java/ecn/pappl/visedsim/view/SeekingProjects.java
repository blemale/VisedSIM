/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.view;

import ecn.pappl.visedsim.view.mainframe.AbstractMainFrame;
import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import ecn.pappl.visedsim.controller.projectviewers.SwingProjectViewerController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

/**
 * Let the user seeking a project
 *
 * @author Denis
 */
public class SeekingProjects extends JDialog {

    private ButtonGroup buttonGroup;
    private Map<String, JRadioButton> buttonMap;
    private JLabel titleLabel;
    private JButton validateButton;
    private List<String> projectsList;
    private AbstractMainFrame mainFrame;
    
    //Integers used in the compact grid
    private static final int PANEL_NUMBER_OF_COLUMN = 1;
    private static final int PANEL_NUMBER_OF_ROW = 3;
    private static final int GRID_INITIAL_X = 5;
    private static final int GRID_INITIAL_Y = 5;
    private static final int DIMENSION_WIDTH = 150;
    private static final int DIMENSION_HEIGHT = 300;

    /**
     * Let the user to choose a project which begin with the acronyme parameter
     * 
     * @param mainFrame
     * @param acronyme 
     */
    public SeekingProjects(AbstractMainFrame mainFrame, String acronyme) {
        super();
        this.mainFrame = mainFrame;
        ProjectListController plc = ProjectListController.getInstance();
        projectsList = plc.getProjectsAcronymsByFirstLetters(acronyme);
        build();
    }

    /**
     * Build the JDialog
     */
    private void build() {
        setTitle(Labels.SEEKING_PROJECTS_TITLE);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setAlwaysOnTop(true);
        setPreferredSize(new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT));
        setContentPane(buildContentPane());
        pack();
    }

    /**
     * Build the panel
     *
     * @return the panel
     */
    private JPanel buildContentPane() {
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        panelCenter.setBackground(Color.white);

        JPanel panel = new JPanel();
        panel.setLayout(new SpringLayout());
        panel.setBackground(Color.white);

        titleLabel = new JLabel(Labels.SEEKING_PROJECTS_TITLE);
        panel.add(titleLabel);

        buttonMap = new HashMap<String, JRadioButton>();
        buttonGroup = new ButtonGroup();
        JPanel panelButton = new JPanel(new SpringLayout());
        panelButton.setBackground(Color.white);

        for (String project : projectsList) {
            buttonMap.put(project, new JRadioButton(project));
            buttonGroup.add(buttonMap.get(project));
            panelButton.add(buttonMap.get(project));
        }

        SpringUtilities.makeCompactGrid(panelButton, projectsList.size(), 1, GRID_INITIAL_X, GRID_INITIAL_Y, 5, 5);

        panel.add(new JScrollPane(panelButton));

        validateButton = new JButton(Labels.SEEKING_PROJECTS_BUTTON);
        validateButton.addActionListener(new java.awt.event.ActionListener() {
            /**
             * Launch the selected project
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateButtonActionPerformed();
            }
        });
        panel.add(validateButton);

        SpringUtilities.makeCompactGrid(panel, PANEL_NUMBER_OF_ROW, PANEL_NUMBER_OF_COLUMN, GRID_INITIAL_X, GRID_INITIAL_Y, 10, 10);

        panelCenter.add(panel);

        return panelCenter;
    }

    /**
     * Launch the selected project
     */
    private void validateButtonActionPerformed() {
        SwingProjectViewerController spvc = SwingProjectViewerController.getInstance();
        ProjectListController plc = ProjectListController.getInstance();
        boolean test = false;
        for (String project : buttonMap.keySet()) {
            if (buttonMap.get(project).isSelected()) {
                spvc.loadProject(plc.getProjectByAcronym(project));
                test = true;
            }
        }
        if (test) {
            this.mainFrame.updateProjectView();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, Labels.SEEKING_PROJECTS_VALIDATION);
        }
    }
}
