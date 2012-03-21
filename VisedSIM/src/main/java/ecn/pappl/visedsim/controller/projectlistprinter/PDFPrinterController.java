/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.projectlistprinter;

import java.io.FileOutputStream;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generate the pdf
 *
 * @author Denis
 */
public class PDFPrinterController {

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private Document document;

    /**
     * Constructor
     * 
     * @param fileName 
     */
    public PDFPrinterController(String fileName) {
        try {
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            addTitlePage(document);

            LinkedList<String> projectList = new LinkedList<String>();
            for (String project : projectList) {
                printProject(project);
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate the pdf for only one project
     * 
     * @param project 
     */
    private void printProject(String project) {
        //TODO : charger la map des critères pour le projet donné
        Map<String, String> criteriaMap = new HashMap<String, String>();
        try {
            addContent(document, criteriaMap, project);
        } catch (DocumentException ex) {
            Logger.getLogger(PDFPrinterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Start a new page
        document.newPage();
    }

    /**
     * Add a title in the first page of a document
     * 
     * @param document
     * @throws DocumentException 
     */
    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Projet de visedSim", catFont));

        addEmptyLine(preface, 1);

        document.add(preface);
    }

    /**
     * Add the content for a page
     * 
     * @param document
     * @param criteriaMap
     * @param project
     * @throws DocumentException 
     */
    private static void addContent(Document document, Map<String, String> criteriaMap, String project) throws DocumentException {
        Anchor anchor = new Anchor(project, catFont);
        anchor.setName(project);

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        Section subCatPart = catPart.addSection(subPara);

        // Add a table
        createTable(subCatPart, criteriaMap);

        // Now add all this to the document
        document.add(catPart);
    }

    /**
     * Create the table for one project
     * 
     * @param subCatPart
     * @param criteriaMap
     * @throws BadElementException 
     */
    private static void createTable(Section subCatPart, Map<String, String> criteriaMap)
            throws BadElementException {

        PdfPTable table = new PdfPTable(criteriaMap.size());

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Critères"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Valeur"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for (String criteria : criteriaMap.keySet()) {
            table.addCell(criteria);
            table.addCell(criteriaMap.get(criteria));
        }

        subCatPart.add(table);
    }

    /**
     * Add empty line in a paragraph
     * 
     * @param paragraph
     * @param number 
     */
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
