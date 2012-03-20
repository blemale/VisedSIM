/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.projectviewers;

import ecn.pappl.visedsim.LaunchApp;
import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.utilities.XMLPersistanceTools;
import java.io.IOException;
import junit.framework.TestCase;

/**
 *
 * @author bastien
 */
public class SwingProjectViewerControllerTest extends TestCase {
    
    public static final String TEST_PROJECT_PATH = "testProject";
    public static final String TEST_CRITERIA_PRESELECTION_PATH =
            "testCriteriaPreselection";
    public static final String TITLE = "title";
    public static final String ACRONYM = "acronym";
    public static final int NUMBER_OF_LINE = 3;
    
    public SwingProjectViewerControllerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        LaunchApp.initApp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getInstance method, of class SwingProjectViewerController.
     */
    public void testGetInstance() {
        System.out.println("getInstance");
        SwingProjectViewerController result =
                SwingProjectViewerController.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of loadProject method, of class SwingProjectViewerController.
     */
    public void testLoadProject() throws IOException {
        System.out.println("loadProject");
        String projectPath = getClass().getClassLoader().getResource(
                TEST_PROJECT_PATH).getPath();
        Project project = (Project) XMLPersistanceTools.decodeFromFile(
                projectPath);
        SwingProjectViewerController instance = SwingProjectViewerController.
                getInstance();
        Project result = instance.loadProject(project);
        assertNotNull(result);
    }

    /**
     * Test of getTitle method, of class SwingProjectViewerController.
     */
    public void testGetTitle() throws Exception {
        System.out.println("getTitle");        
        Project project = loadTestProject();
        SwingProjectViewerController instance = SwingProjectViewerController.
                getInstance();
        instance.loadProject(project);
        String expResult = project.getCriteriaMap().get(TITLE).get(0);
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAcronym method, of class SwingProjectViewerController.
     */
    public void testGetAcronym() throws Exception {
        System.out.println("getTitle");
        Project project = loadTestProject();
        SwingProjectViewerController instance = SwingProjectViewerController.
                getInstance();
        instance.loadProject(project);
        String expResult = project.getCriteriaMap().get(ACRONYM).get(0);
        String result = instance.getAcronym();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCriteria method, of class SwingProjectViewerController.
     */
    public void testGetCriteria() throws Exception {
        System.out.println("getCriteria");
        CriteriaPreselection criteriaPreselection =
                loadTesCriteriaPreselection();
        SwingProjectViewerController instance = SwingProjectViewerController.
                getInstance();
        instance.loadProject(loadTestProject());
        Object[][] result = instance.getCriteria(criteriaPreselection);
        assertTrue(result.length == NUMBER_OF_LINE);
    }
    
    private Project loadTestProject() throws Exception {
        String testProjectPath = getClass().getClassLoader().
                getResource(
                TEST_PROJECT_PATH).getPath();
        return (Project) XMLPersistanceTools.decodeFromFile(testProjectPath);
    }
    
    private CriteriaPreselection loadTesCriteriaPreselection() throws Exception {
        String testCriteriaPreselectionPath = getClass().getClassLoader().
                getResource(TEST_CRITERIA_PRESELECTION_PATH).getPath();
        return (CriteriaPreselection) XMLPersistanceTools.decodeFromFile(
                testCriteriaPreselectionPath);
    }
}
