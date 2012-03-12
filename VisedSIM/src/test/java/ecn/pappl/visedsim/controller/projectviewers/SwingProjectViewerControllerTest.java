/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.projectviewers;

import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.utilities.XMLPersistanceTools;
import junit.framework.TestCase;

/**
 *
 * @author bastien
 */
public class SwingProjectViewerControllerTest extends TestCase {
    
    public SwingProjectViewerControllerTest(String testName) {
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
     * Test of getInstance method, of class SwingProjectViewerController.
     */
    public void testGetInstance() {
        System.out.println("getInstance");
        SwingProjectViewerController expResult = null;
        SwingProjectViewerController result =
                SwingProjectViewerController.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadProject method, of class SwingProjectViewerController.
     */
    public void testLoadProject() {
        System.out.println("loadProject");
        Project project = null;
        SwingProjectViewerController instance = null;
        Project expResult = null;
        Project result = instance.loadProject(project);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitle method, of class SwingProjectViewerController.
     */
    public void testGetTitle() {
        System.out.println("getTitle");
        SwingProjectViewerController instance = null;
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAcronym method, of class SwingProjectViewerController.
     */
    public void testGetAcronym() {
        System.out.println("getAcronym");
        SwingProjectViewerController instance = null;
        String expResult = "";
        String result = instance.getAcronym();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCriteria method, of class SwingProjectViewerController.
     */
    public void testGetCriteria() throws Exception {
        System.out.println("getCriteria");
        CriteriaPreselection criteriaPreselection = loadTesCriteriaPreselection();
        SwingProjectViewerController instance = SwingProjectViewerController.getInstance();
        instance.loadProject(loadTestProject());
        Object[][] expResult = null;
        Object[][] result = instance.getCriteria(criteriaPreselection);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
        private Project loadTestProject() throws Exception {
        String testProjectPath = getClass().getClassLoader().
                getResource(
                "testProject").getPath();
        return (Project) XMLPersistanceTools.decodeFromFile(testProjectPath);
    }

    private CriteriaPreselection loadTesCriteriaPreselection() throws Exception {
        String testCriteriaPreselectionPath = getClass().getClassLoader().
                getResource("testCriteriaPreselection").getPath();
        return (CriteriaPreselection) XMLPersistanceTools.decodeFromFile(
                testCriteriaPreselectionPath);
    }
}
