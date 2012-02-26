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
public final class FileLoader extends GUI {
    
    private JLabel listProjectLabel, newProjectListLabel;    
    private JComboBox projectListComboBox;
    private JButton loadSavedListButton,chooseNewListButton, loadNewListButton;
    private JTextField filePathField;
    private JFileChooser fileChooser;
    private final int textColumnLenght = 10;
    
    //TODO Get the content of the projectListArray
    private String[] projectListArray;
    
    /**
     * Constructor of FileLoader
     */
    public FileLoader(){
        super();
        build();
    }
    
    @Override
    protected void build(){
        super.build();
        setTitle(Labels.FILE_LOADER_TITLE);
        
        fileChooser = new JFileChooser();

	FileNameExtensionFilter filter = new FileNameExtensionFilter("XML File", "xml");
	fileChooser.setFileFilter(filter);
    }
    
    @Override
    protected JPanel buildContentPane(){
        super.buildContentPane();
        
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
