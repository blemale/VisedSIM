/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.projectlistprinter;

import java.io.FileOutputStream;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfWriter;
import ecn.pappl.visedsim.Configuration;
import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import ecn.pappl.visedsim.controller.projectlist.ProjectListController;
import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.struct.ProjectList;
import ecn.pappl.visedsim.utilities.PDFTools;
import ecn.pappl.visedsim.utilities.ProjectTools;
import java.util.*;

/**
 * Generate the pdf
 *
 * @author Denis
 */
public class PDFPrinterController {

    private static final Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static final Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static final Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN,
            12,
            Font.BOLD);
    private static final String TITLE = "title";
    private static final String ACRONYM = "acronym";
    /**
     * Unique instance of {@link ProjectListController}.
     */
    private volatile static PDFPrinterController instance = null;

    /**
     * The default constructor.
     */
    private PDFPrinterController() {
        super();
    }

    /**
     * Get the unique instance of {@link ProjectListController}.
     * <p/>
     * @return the unique instance of {@link ProjectListController}.
     */
    public static PDFPrinterController getInstance() {
        if (PDFPrinterController.instance == null) {
            synchronized (ProjectListController.class) {
                PDFPrinterController.instance = new PDFPrinterController();
            }
        }

        return PDFPrinterController.instance;
    }

    /**
     * Print a {@link ProjectList}.
     * <p/>
     * @param fileName
     */
    public void printProjectList(String fileName) {
        try {
            CriteriaPreselection criteriaPreselection =
                    CriteriaPreselectionController.getInstance().
                    getCriteriaPreselection();
            ProjectList projectList = ProjectListController.getInstance().
                    getProjectList();

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            for (Project project : projectList.getProjectList()) {
                addProjectToPDFDoc(document, project, criteriaPreselection);
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addProjectToPDFDoc(Document document, Project project,
            CriteriaPreselection criteriaPreselection) throws DocumentException {
        int j = 2;
        int i = ProjectTools.getNumberOfCriteriaLines(project,
                criteriaPreselection);
        int index = 0;
        String[][] criteriaArray = new String[i][j];
        Map<String, List<String>> selectedCriteria = ProjectTools.
                getSelectedCriteria(project, criteriaPreselection);
        String path = Configuration.I18N_FOLDER + "/Criteria";
        ResourceBundle bundle = ResourceBundle.getBundle(path,
                Locale.getDefault());
        Map<String, String> criteriaNamesMap = new HashMap<String, String>();
        for (String criteria : selectedCriteria.keySet()) {
            criteriaNamesMap.put(bundle.getString(criteria), criteria);
        }
        List<String> criteriaNames = new ArrayList<String>(criteriaNamesMap.
                keySet());
        Collections.sort(criteriaNames);
        for (String criteriaName : criteriaNames) {
            String criteria = criteriaNamesMap.get(criteriaName);
            for (String value : selectedCriteria.get(criteria)) {
                criteriaArray[index][0] = criteriaName;
                criteriaArray[index][1] = value;
                index++;
            }
        }
        String projectTitle = "";
        if (project.getCriteriaMap().containsKey(TITLE)) {
            projectTitle = project.getCriteriaMap().get(TITLE).get(0);
        }
        String projectAcronym = "";
        if (project.getCriteriaMap().containsKey(ACRONYM)) {
            projectAcronym = project.getCriteriaMap().get(ACRONYM).get(0);
        }
        String projectName = projectAcronym+" : "+projectTitle;
        
        Anchor anchor = new Anchor(projectTitle, catFont);
        anchor.setName(projectName);

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        Section subCatPart = catPart.addSection(subPara);

        // Add a table
        String[] columnName = new String[]{"Critère", "Valeur"};
        PDFTools.createTable(subCatPart, criteriaArray, columnName);

        // Now add all this to the document
        document.add(catPart);
        document.newPage();
    }
}
