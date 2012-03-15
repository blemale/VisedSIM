/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

/**
 * Class processing the extraction of the datas contained in the input Excel File.
 * 
 * @author bastien
 */
public final class ExcelDatasExtractor {
    
    private ExcelDatasExtractor(){        
    }

    /**
     * Extracts the datas of an Excel {@link Sheet} and saves them into a {@link Map} 
     * @param file the {@link File} to parse.
     * @return the {@link Map} containing the Excel {@link Sheet}'s datas 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InvalidFormatException 
     */
    public static Map<Integer, Map<Integer, String>> procFile(File file) throws FileNotFoundException, IOException, InvalidFormatException {
        Map<Integer, Map<Integer, String>> datas = new HashMap<Integer, Map<Integer, String>>();
        Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            int rowNum = row.getRowNum();
            datas.put(rowNum, new HashMap<Integer, String>());
            for (Cell cell : row) {
                datas.get(rowNum).put(cell.getColumnIndex(), cellToString(cell));
            }
        }

        return datas;
    }

    /**
     * Converts the value of a {@link Cell} into a {@link String}.
     * @param cell the {@link Cell} to convert.
     * @return the {@link String} representing the {@link Cell} value. 
     */
    private static String cellToString(Cell cell) {
        String cellToString = "";
        if (cell != null) {
            int cellType = cell.getCellType();
            switch (cellType) {
                case Cell.CELL_TYPE_BOOLEAN:
                case Cell.CELL_TYPE_NUMERIC:
                case Cell.CELL_TYPE_STRING:
                    DataFormatter df = new DataFormatter();
                    cellToString = df.formatCellValue(cell);
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    // Force evaluation of the formula, which mutates the cell, and send it back through...
                    cell.getRow().getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator().evaluateInCell(cell);
                    cellToString = cellToString(cell);
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                case Cell.CELL_TYPE_ERROR:
                    byte errorValue = cell.getErrorCellValue();
                    cellToString = FormulaError.forInt(errorValue).getString();
                    break;
                default:
                    break;
            }
        }
        return cellToString;
    }
}
