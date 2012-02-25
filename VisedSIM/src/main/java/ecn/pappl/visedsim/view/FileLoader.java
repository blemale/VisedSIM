package ecn.pappl.visedsim.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Denis
 */
public class FileLoader extends JFrame {
    
    private JLabel listProjectLabel, newProjectListLabel;    
    private JComboBox projectListComboBox;
    private JButton loadSavedListButton,chooseNewListButton, loadNewListButton;
    private JTextField filePathField;
    private JFileChooser fileChooser;
    private final int textColumnLenght = 10;    
    private String[] projectListArray;
    
    /**
     * Constructor of FileLoader
     */
    public FileLoader(){
        super();
        build();
    }
    
    /**
     * Create the first frame of the program
     */
    private void build(){
        setTitle("VisedSIM");
	setLocationRelativeTo(null);
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setContentPane(buildContentPane());
	pack();

	fileChooser = new JFileChooser();

	FileNameExtensionFilter filter = new FileNameExtensionFilter("XML File", "xml");
	fileChooser.setFileFilter(filter);
    }
    
    /**
     * Build the panel
     * 
     * @return the panel 
     */
    private JPanel buildContentPane(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        
        //1st line
        listProjectLabel = new JLabel(Labels.LIST_PROJECT); 
        panel.add(listProjectLabel);
        
        newProjectListLabel = new JLabel(Labels.NEW_PROJECT_LIST);
        panel.add(newProjectListLabel);
        
        //2nd line
        projectListComboBox = new JComboBox(projectListArray);
        panel.add(projectListComboBox);
        
        JPanel chooseFilePanel = new JPanel();
        chooseFilePanel.setLayout(new FlowLayout());
        chooseFilePanel.setBackground(Color.white);
        
        filePathField = new JTextField();
        filePathField.setColumns(textColumnLenght);
        chooseFilePanel.add(filePathField);
        
        chooseNewListButton = new JButton(new ChooseFileOption(this, Labels.CHOOSE_NEW_LIST_BUTTON));
        chooseFilePanel.add(chooseNewListButton);
        
        panel.add(chooseFilePanel);
        
        //3rd line
        loadSavedListButton = new JButton(Labels.LOAD_SAVED_LIST_BUTTON);
        panel.add(loadSavedListButton);
        
        loadNewListButton = new JButton(Labels.LOAD_NEW_LIST_BUTTON);
        panel.add(loadNewListButton);
        
        SpringUtilities.makeCompactGrid(panel, 3, 2, 5, 5, 0, 0);
        return panel;
    }
    
    /**
     * get fileChooser;
     * 
     * @return 
     */
    public JFileChooser getFileChooser(){
        return fileChooser;
    }
    
    /**
     * get filePathField
     * 
     * @return 
     */
    public JTextField getFilePathField(){
        return filePathField;
    }
    
}
