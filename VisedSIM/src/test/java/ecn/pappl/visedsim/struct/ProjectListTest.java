/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.struct;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Denis
 */
public class ProjectListTest extends TestCase {
    
    ProjectList pl;
    Project p1;
    Project p2;
    
    public ProjectListTest(String testName) {
        super(testName);
        pl = new ProjectList();
        assertTrue(pl != null);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        p1 = new Project();
        p1.setAcronym("PPP1");
        p1.setTitle("MonProjet1");
        
        p2 = new Project();
        p2.setAcronym("PPP2");
        p2.setTitle("MonProjet2");
        
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of setProjectList method, of class ProjectList.
     */
    public void testSetProjectList() {
        System.out.println("setProjectList");
        
        ArrayList<Project> l = new ArrayList<Project>();
        l.add(p1); l.add(p2);
        pl.setProjectList(l);
        assertTrue(pl.getProjectList().size() == 2);
    }
}
