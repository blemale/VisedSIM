/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.io;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author bastien
 */
public class ExcelProcessing {

    public static void procFile(File file) {
        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        String message = String.format(
                                "%s, Sheet[%s], Row[%d], Cell[%d], Contents[%s]",
                                file.getName(),
                                sheet.getSheetName(),
                                row.getRowNum(),
                                cell.getColumnIndex(),
                                cellToString(cell));
                        System.out.println(message);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
                    System.err.println(String.format("Unexpected Cell Type [%d] skipped.", cellType));
                    break;
            }
        }
        return cellToString;
    }
}
