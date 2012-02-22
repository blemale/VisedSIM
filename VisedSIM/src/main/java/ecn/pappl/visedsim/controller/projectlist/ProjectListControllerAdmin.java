/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.projectlist;

import ecn.pappl.visedsim.io.ExcelDatasExtractor;
import ecn.pappl.visedsim.io.MapCleaner;
import ecn.pappl.visedsim.io.MapConverter;
import ecn.pappl.visedsim.struct.ProjectList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * Class representing a controller for {@link ProjectList} in Admin mode.
 *
 * @author bastien
 */
public class ProjectListControllerAdmin extends ProjectListController implements ProjectListExcelLoader {

    /**
     * Default constructor.
     */
    public ProjectListControllerAdmin() {
        super();
    }

    public ProjectList loadExcelProjectList(String fileName, Map<String, List<Integer>> columnsOrder, int firstCellRow, int firstCellColl) throws FileNotFoundException, IOException, InvalidFormatException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Map<Integer, Map<Integer, String>> map = ExcelDatasExtractor.procFile(new File(fileName));
        for (int i = 0; i < firstCellRow; i++) {
            MapCleaner.removeRow(map, i);
        }
        for (int j = 0; j < firstCellColl; j++) {
            MapCleaner.removeCol(map, j);
        }
        this.projectList = MapConverter.convertMapToProjectList(map, columnsOrder);
        return this.projectList;
    }
}
