/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller;

import ecn.pappl.visedsim.struct.ProjectList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Interface representing a loader of {@link ProjectList} from a Excel file.
 *
 * @author bastien
 */
public interface ProjectListExcelLoader {

    /**
     * Loads a {@link ProjectList} from a given Excel file.
     *
     * @param fileName the name of the file to load.
     * @param columnsOrder the order of the columns in the given Excel file.
     * @param firstCellRow the row's number of the first {@link Cell} to
     * consider.
     * @param firstCellColl the columns's number of the first {@link Cell} to
     * consider.
     * @return a {@link ProjectList}.
     */
    ProjectList loadExcelProjectList(String fileName, Map<String, List<Integer>> columnsOrder, int firstCellRow, int firstCellColl) throws FileNotFoundException, IOException, InvalidFormatException;
}
