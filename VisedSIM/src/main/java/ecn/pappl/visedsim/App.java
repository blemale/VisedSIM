package ecn.pappl.visedsim;

import ecn.pappl.visedsim.io.ExcelDatasExtractor;
import ecn.pappl.visedsim.io.MapCleaner;
import ecn.pappl.visedsim.io.MapConverter;
import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.struct.ProjectList;
import ecn.pappl.visedsim.utilities.XMLTools;
import ecn.pappl.visedsim.view.ChooseCriteria;
import ecn.pappl.visedsim.view.ConfidentialProjects;
import ecn.pappl.visedsim.view.FileLoader;
import ecn.pappl.visedsim.view.NewJFrame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


public class App 
{
    public static void main( String[] args ) throws Exception
    {
        /*long l1 = System.currentTimeMillis();
        Map<Integer, Map<Integer, String>> datas = ExcelDatasExtractor.procFile(new File("Data-CE-v2.xls"));
        MapCleaner.removeRow(datas, 0);
        MapCleaner.removeRow(datas, 1);
        MapCleaner.removeRow(datas, 2);
        
        Map<String, List<Integer>> colOrder = ( Map<String, List<Integer>>)XMLTools.decodeFromFile("myColumnsOrder.xml");
        
        ProjectList projectList = MapConverter.convertMapToProjectList(datas, colOrder);
        
        XMLTools.encodeToFile(projectList, "myProjectsList.xml");
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l1);*/
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        FileLoader fileLoader = new FileLoader();
        fileLoader.setVisible(true);
        
        List<String> listCriteria = new ArrayList<String>();
        listCriteria.add("Nom");
        listCriteria.add("Exemple");
        listCriteria.add("Exemple 2");
        listCriteria.add("Test");
        listCriteria.add("Dernier point");
        listCriteria.add("Nom");
        listCriteria.add("Exemple 2");
        listCriteria.add("Test");
        listCriteria.add("Nom");
        
        List<String> preselectionCriteria = new ArrayList<String>();
        preselectionCriteria.add("Sélection");
        preselectionCriteria.add("Aucun");
        ChooseCriteria chooseCriteria = new ChooseCriteria(listCriteria, preselectionCriteria);
        chooseCriteria.setVisible(true);
        
        List<String> projectList = new ArrayList<String>();
        projectList.add("MPB4");
        projectList.add("MPB2");
        projectList.add("MPB3");
        projectList.add("MPB5");
        projectList.add("MPB2");
        projectList.add("MPB90");
        projectList.add("MPBYT7");
        projectList.add("MPB");
        projectList.add("MPB6");
        projectList.add("MPB5");
        projectList.add("MPB2");
        
        ConfidentialProjects conf = new ConfidentialProjects(projectList);
        conf.setVisible(true);
    }       
}