/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.criteriapreselection;

import ecn.pappl.visedsim.struct.CriteriaPreselection;
import java.io.File;
import junit.framework.TestCase;

/**
 *
 * @author bastien
 */
public class CriteriaPreselectionControllerTest extends TestCase {

    public CriteriaPreselectionControllerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        File testFile = new File("testSaveCriteriaPreselection");
        testFile.delete();
    }

    /**
     * Test of getInstance method, of class CriteriaPreselectionController.
     */
    public void testGetInstance() {
        System.out.println("getInstance");
        CriteriaPreselectionController result =
                CriteriaPreselectionController.getInstance();
        assertNotNull(result);
        assertTrue(
                result.getClass().equals(CriteriaPreselectionController.class));
    }

    /**
     * Test of createCriteriaPreselection method, of class
     * CriteriaPreselectionController.
     */
    public void testCreateCriteriaPreselection() throws Exception {
        System.out.println("createCriteriaPreselection");
        CriteriaPreselectionController instance =
                CriteriaPreselectionController.getInstance();
        CriteriaPreselection result = instance.createCriteriaPreselection();
        assertNotNull(result);
    }

    /**
     * Test of initCriteriaPreselection method, of class
     * CriteriaPreselectionController.
     */
    public void testInitCriteriaPreselection() throws Exception {
        System.out.println("createCriteriaPreselection");
        CriteriaPreselectionController instance =
                CriteriaPreselectionController.getInstance();
        CriteriaPreselection result = instance.initCriteriaPreselection();
        assertNotNull(result);
    }

    /**
     * Test of loadCriteriaPreselection method, of class
     * CriteriaPreselectionController.
     */
    public void testLoadCriteriaPreselection() throws Exception {
        System.out.println("loadCriteriaPreselection");
        String fileName = "testCriteriaPreselection";
        CriteriaPreselectionController instance =
                CriteriaPreselectionController.getInstance();
        CriteriaPreselection result =
                instance.loadCriteriaPreselection(fileName);
        assertNotNull(result);
    }

    /**
     * Test of saveCriteriaPreselection method, of class
     * CriteriaPreselectionController.
     */
    public void testSaveAndLoadCriteriaPreselection() throws Exception {
        System.out.println("saveCriteriaPreselection");
        String fileName = "testSaveCriteriaPreselection";
        CriteriaPreselectionController instance =
                CriteriaPreselectionController.getInstance();
        instance.createCriteriaPreselection();
        instance.saveCriteriaPreselection(fileName);
        instance.loadCriteriaPreselection(fileName);
    }
}
