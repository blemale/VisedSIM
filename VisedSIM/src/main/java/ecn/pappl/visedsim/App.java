package ecn.pappl.visedsim;

import ecn.pappl.visedsim.io.ExcelDatasExtractor;
import ecn.pappl.visedsim.io.MapCleaner;
import ecn.pappl.visedsim.io.MapConverter;
import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.struct.ProjectList;
import ecn.pappl.visedsim.utilities.XMLTools;
import ecn.pappl.visedsim.view.FileLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * Hello world!
 *
 */
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
        
        
        FileLoader fileLoader = new FileLoader();
        fileLoader.setVisible(true);
    }
}