/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.utilities;

import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.struct.Project;
import junit.framework.TestCase;

/**
 *
 * @author bastien
 */
public class ProjectToolsTest extends TestCase {

    public ProjectToolsTest(String testName) {
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
     * Test of applyCriteriaPreselection method, of class ProjectTools.
     */
    public void testApplyCriteriaPreselection() throws Exception {
        System.out.println("applyCriteriaPreselection");
        String testProjectPath = getClass().getClassLoader().
                getResource(
                "testProject").getPath();
        Project project = (Project) XMLTools.decodeFromFile(testProjectPath);
        String testCriteriaPreselectionPath = getClass().getClassLoader().
                getResource("testCriteriaPreselection").getPath();
        CriteriaPreselection criteriaPreselection =
                (CriteriaPreselection) XMLTools.decodeFromFile(
                testCriteriaPreselectionPath);
        Project result =
                ProjectTools.applyCriteriaPreselection(project,
                criteriaPreselection);
        project.setAcronym("");
        assertEquals(project.getTitle(), result.getTitle());
        assertEquals(project.getAcronym(), result.getAcronym());
        assertEquals(project.getCompetitivePoles(), result.getCompetitivePoles());
    }

    /**
     * Test of getNumberOfCriteriaLines method, of class ProjectTools.
     */
    public void testGetNumberOfCriteriaLines() throws Exception {
        System.out.println("getNumberOfCriteriaLines");
        String testProjectPath = getClass().getClassLoader().
                getResource(
                "testProject").getPath();
        Project project = (Project) XMLTools.decodeFromFile(testProjectPath);
        String testCriteriaPreselectionPath = getClass().getClassLoader().
                getResource("testCriteriaPreselection").getPath();
        CriteriaPreselection cp =
                (CriteriaPreselection) XMLTools.decodeFromFile(
                testCriteriaPreselectionPath);
        int expResult = 3;
        int result = ProjectTools.getNumberOfCriteriaLines(project, cp);
        assertEquals(expResult, result);
    }

    /**
     * Test of fillArrayWithSelectedCriteria method, of class ProjectTools.
     */
    public void testFillArrayWithSelectedCriteria() throws Exception {
        System.out.println("fillArrayWithSelectedCriteria");
        String testProjectPath = getClass().getClassLoader().
                getResource(
                "testProject").getPath();
        Project project = (Project) XMLTools.decodeFromFile(testProjectPath);
        String testCriteriaPreselectionPath = getClass().getClassLoader().
                getResource("testCriteriaPreselection").getPath();
        CriteriaPreselection cp =
                (CriteriaPreselection) XMLTools.decodeFromFile(
                testCriteriaPreselectionPath);
        Object[][] expectedArray = new Object[3][2];
        expectedArray[0][0]="competitivePoles";expectedArray[0][1]="competitivePoles1";
        expectedArray[1][0]="competitivePoles";expectedArray[1][1]="competitivePoles2";
        expectedArray[2][0]="title";expectedArray[2][1]="title";
        Object[][] array = new Object[3][2];
        ProjectTools.fillArrayWithSelectedCriteria(project, cp, array);
        assertEquals("competitivePoles", array[1][0]);
        assertEquals("competitivePoles1", array[1][1]);
        assertEquals("competitivePoles", array[2][0]);
        assertEquals("competitivePoles2", array[2][1]);
        assertEquals("title", array[0][0]);
        assertEquals("title", array[0][1]);
    }
}
