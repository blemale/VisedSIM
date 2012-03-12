/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.projectlist;

import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.struct.ProjectList;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author bastien
 */
public class ProjectListControllerTest extends TestCase {
    private static final String ACRONYM = "acronym";

    public ProjectListControllerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
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
                "testProjectList").getPath();
        ProjectListController instance = ProjectListController.getInstance();
        ProjectList result = instance.loadProjectList(fileName);
        assertNotNull(result);
    }

    /**
     * Test of getProjectByAcronym method, of class ProjectListController.
     */
    public void testGetProjectByAcronym() throws Exception {
        System.out.println("getProjectByAcronym");
        String acronym = "MBP1";
        ProjectListController instance = ProjectListController.getInstance();
        String fileName = getClass().getClassLoader().
                getResource(
                "testProjectList").getPath();
        instance.loadProjectList(fileName);
        Project result = instance.getProjectByAcronym(acronym);
        assertTrue(result.getCriteriaMap().containsKey(ACRONYM));
        assertEquals("MBP1", result.getCriteriaMap().get(ACRONYM).get(0));
    }

    /**
     * Test of getProjectsAcronyms method, of class ProjectListController.
     */
    public void testGetProjectsAcronyms() throws Exception {
        System.out.println("getProjectsAcronyms");
        ProjectListController instance = ProjectListController.getInstance();
        String fileName = getClass().getClassLoader().
                getResource(
                "testProjectList").getPath();
        instance.loadProjectList(fileName);
        List result = instance.getProjectsAcronyms();
        assertEquals(87, result.size());
    }

    /**
     * Test of getProjectsAcronymsByFirstLetters method, of class
     * ProjectListController.
     */
    public void testGetProjectsAcronymsByFirstLetters() throws Exception{
        System.out.println("getProjectsAcronymsByFirstLetters");
        String firstLetters = "MBP1";
        ProjectListController instance = ProjectListController.getInstance();
        String fileName = getClass().getClassLoader().
                getResource(
                "testProjectList").getPath();
        instance.loadProjectList(fileName);
        List result = instance.getProjectsAcronymsByFirstLetters(firstLetters);
        assertEquals(11, result.size());
    }

    /**
     * Test of loadExcelProjectList method, of class ProjectListController.
     */
    public void testLoadExcelProjectList() throws Exception {
        System.out.println("loadExcelProjectList");
        String fileName = "";
        Map<String, List<Integer>> columnsOrder = null;
        int firstCellRow = 0;
        int firstCellColl = 0;
        ProjectListController instance = null;
        ProjectList expResult = null;
        ProjectList result =
                instance.loadExcelProjectList(fileName, columnsOrder,
                firstCellRow, firstCellColl);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveProjectList method, of class ProjectListController.
     */
    public void testSaveProjectList() throws Exception {
        System.out.println("saveProjectList");
        String fileName = "";
        ProjectListController instance = null;
        instance.saveProjectList(fileName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveProjectListWithInterrestConflicts method, of class
     * ProjectListController.
     */
    public void testSaveProjectListWithInterrestConflicts() throws Exception {
        System.out.println("saveProjectListWithInterrestConflicts");
        String fileName = "";
        Map<String, Boolean> conflicts = null;
        CriteriaPreselection conflictCriteria = null;
        ProjectListController instance = null;
        instance.saveProjectListWithInterrestConflicts(fileName, conflicts,
                conflictCriteria);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
