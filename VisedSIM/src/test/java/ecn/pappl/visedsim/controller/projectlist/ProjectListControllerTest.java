/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.projectlist;

import ecn.pappl.visedsim.Configuration;
import ecn.pappl.visedsim.LaunchApp;
import ecn.pappl.visedsim.controller.criteriapreselection.CriteriaPreselectionController;
import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.struct.ProjectList;
import ecn.pappl.visedsim.utilities.XMLPersistanceTools;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author bastien
 */
public class ProjectListControllerTest extends TestCase {

    private static final String TEST_FILE_PATH = "testProjectList";
    private static final String ACRONYM = "acronym";
    private static final String ACRONYM_VALUE = "MBP1";
    private static final int NUMBER_OF_PROJECT = 2;
    private static final int NUMBER_OF_MATCHING_PROJECT = 1;
    private static final String TEST_EXCEL_FILE_PATH = "testData.xls";
    private static final int FIRST_ROW = 3;
    private static final int FIRST_COL = 0;
    private static final int NUMBER_OF_PROJECT_IN_EXCEL = 2;
    private static final String TEST_SAVE_FILE_PATH = "testProjectList";
    private static final Map<String, Boolean> CONFLICT_MAP =
            new HashMap<String, Boolean>();
    private static final String ACRONYM1 = "MBP1";
    private static final String ACRONYM2 = "MBP2";

    public ProjectListControllerTest(String testName) {
        super(testName);
        CONFLICT_MAP.put(ACRONYM1, Boolean.TRUE);
        CONFLICT_MAP.put(ACRONYM2, Boolean.FALSE);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        LaunchApp.initApp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        File testFile = new File(TEST_SAVE_FILE_PATH);
        testFile.delete();
    }

    /**
     * Test of getInstance method, of class ProjectListController.
     */
    public void testGetInstance() {
        System.out.println("getInstance");
        ProjectListController result = ProjectListController.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of loadProjectList method, of class ProjectListController.
     */
    public void testLoadProjectList() throws Exception {
        System.out.println("loadProjectList");
        String fileName = getClass().getClassLoader().
                getResource(
                TEST_FILE_PATH).getPath();
        ProjectListController instance = ProjectListController.getInstance();
        ProjectList result = instance.loadProjectList(fileName);
        assertNotNull(result);
    }

    /**
     * Test of getProjectByAcronym method, of class ProjectListController.
     */
    public void testGetProjectByAcronym() throws Exception {
        System.out.println("getProjectByAcronym");
        ProjectListController instance = ProjectListController.getInstance();
        String fileName = getClass().getClassLoader().
                getResource(
                TEST_FILE_PATH).getPath();
        instance.loadProjectList(fileName);
        Project result = instance.getProjectByAcronym(ACRONYM_VALUE);
        assertTrue(result.getCriteriaMap().containsKey(ACRONYM));
        assertEquals(ACRONYM_VALUE, result.getCriteriaMap().get(ACRONYM).get(0));
    }

    /**
     * Test of getProjectsAcronyms method, of class ProjectListController.
     */
    public void testGetProjectsAcronyms() throws Exception {
        System.out.println("getProjectsAcronyms");
        ProjectListController instance = ProjectListController.getInstance();
        String fileName = getClass().getClassLoader().
                getResource(
                TEST_FILE_PATH).getPath();
        instance.loadProjectList(fileName);
        List result = instance.getProjectsAcronyms();
        assertEquals(NUMBER_OF_PROJECT, result.size());
    }

    /**
     * Test of getProjectsAcronymsByFirstLetters method, of class
     * ProjectListController.
     */
    public void testGetProjectsAcronymsByFirstLetters() throws Exception {
        System.out.println("getProjectsAcronymsByFirstLetters");
        ProjectListController instance = ProjectListController.getInstance();
        String fileName = getClass().getClassLoader().
                getResource(
                TEST_FILE_PATH).getPath();
        instance.loadProjectList(fileName);
        List result = instance.getProjectsAcronymsByFirstLetters(ACRONYM_VALUE);
        assertEquals(NUMBER_OF_MATCHING_PROJECT, result.size());
    }

    /**
     * Test of loadExcelProjectList method, of class ProjectListController.
     */
    public void testLoadExcelProjectList() throws Exception {
        System.out.println("loadExcelProjectList");
        String fileName = getClass().getClassLoader().
                getResource(
                TEST_EXCEL_FILE_PATH).getPath();
        String columnsOrderPath = getClass().getClassLoader().
                getResource(Configuration.COLUMNS_ORDER_FILE_PATH).getPath();
        Map<String, List<Integer>> columnsOrder =
                (Map<String, List<Integer>>) XMLPersistanceTools.decodeFromFile(
                columnsOrderPath);
        int firstCellRow = FIRST_ROW;
        int firstCellColl = FIRST_COL;
        ProjectListController instance = ProjectListController.getInstance();
        ProjectList result =
                instance.loadExcelProjectList(fileName, columnsOrder,
                firstCellRow, firstCellColl);
        assertTrue(result.getProjectList().size() == NUMBER_OF_PROJECT_IN_EXCEL);
    }

    /**
     * Test of saveProjectList method, of class ProjectListController.
     */
    public void testLoadAndSaveProjectList() throws Exception {
        System.out.println("saveProjectList");
        String fileName = TEST_SAVE_FILE_PATH;
        ProjectListController instance = ProjectListController.getInstance();
        String projectListPath = getClass().getClassLoader().
                getResource(TEST_FILE_PATH).getPath();
        instance.loadProjectList(projectListPath);
        instance.saveProjectList(fileName);
        assertTrue(new File(TEST_SAVE_FILE_PATH).exists());
    }

    /**
     * Test of saveProjectListWithInterrestConflicts method, of class
     * ProjectListController.
     */
    public void testSaveProjectListWithInterrestConflicts() throws Exception {
        System.out.println("saveProjectListWithInterrestConflicts");
        String fileName = TEST_SAVE_FILE_PATH;
        ProjectListController instance = ProjectListController.getInstance();
        String projectListPath = getClass().getClassLoader().
                getResource(TEST_FILE_PATH).getPath();
        instance.loadProjectList(projectListPath);
        Map<String, Boolean> conflicts = CONFLICT_MAP;
        CriteriaPreselection conflictCriteria = CriteriaPreselectionController.
                getInstance().getCriteriaPreselection();
        instance.saveProjectListWithInterrestConflicts(fileName, conflicts,
                conflictCriteria);
        assertTrue(new File(TEST_SAVE_FILE_PATH).exists());
    }
}
