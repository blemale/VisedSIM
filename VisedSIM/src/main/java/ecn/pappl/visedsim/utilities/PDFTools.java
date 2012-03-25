/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.utilities;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.*;

/**
 *
 * @author bastien
 */
public class PDFTools {
    /**
     * Add a title in the first page of a document
     * 
     * @param document
     * @throws DocumentException 
     */
    public static void addTitlePage(Document document, String title, Font font)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph(title, font));

        addEmptyLine(preface, 1);

        document.add(preface);
    }

    /**
     * Create the table for one project
     * 
     * @param subCatPart
     * @param criteriaMap
     * @throws BadElementException 
     */
    public static void createTable(Chapter catPara, String[][] tableContent, String[] columnName)
            throws BadElementException {

        PdfPTable table = new PdfPTable(2);
        PdfPCell c1 = new PdfPCell(new Phrase(columnName[0]));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(new Phrase(columnName[1])));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for (int i = 0; i<tableContent.length;i++) {
            table.addCell(tableContent[i][0]);
            table.addCell(tableContent[i][1]);
        }

        //subCatPart.add(table);
        //subPara.add(table);
        catPara.add(table);
    }

    /**
     * Add empty line in a paragraph
     * 
     * @param paragraph
     * @param number 
     */
    public static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    
    /**
     * Add empty line in a paragraph
     * 
     * @param chapter
     * @param number 
     */
    public static void addEmptyLine(Chapter chapter, int number) {
        for (int i = 0; i < number; i++) {
            chapter.add(new Paragraph(" "));
        }
    }
}
