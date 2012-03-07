/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.utilities;

import java.util.LinkedList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author bastien
 */
public class FileToolsTest extends TestCase {
    
    public FileToolsTest(String testName) {
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
     * Test of getFilesNamesInDirectory method, of class FileTools.
     */
    public void testGetFilesNamesInDirectory() {
        System.out.println("getFilesNamesInDirectory");
        String directoryPath = getClass().getClassLoader().getResource(
                "testFolder/file1").getPath();
        directoryPath = directoryPath.split("file1")[0];       
        List<String> expResult = new LinkedList<String>();
        expResult.add("file1"); expResult.add("file2");
        List<String> result = FileTools.getFilesNamesInDirectory(directoryPath);
        assertEquals(expResult, result);
    }
}
