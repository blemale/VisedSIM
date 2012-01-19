package ecn.pappl.visedsim;

import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.struct.ProjectList;
import ecn.pappl.visedsim.utilities.XMLTools;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
        Project p1 = new Project();
        p1.setAcronym("PPP1");
        p1.setTitle("MonProjet1");
        
        Project p2 = new Project();
        p1.setAcronym("PPP2");
        p1.setTitle("MonProjet2");
        
        ProjectList pl = new ProjectList();
        ArrayList<Project> l = new ArrayList<Project>();
        l.add(p1); l.add(p2);
        pl.setProjectList(l);
        
        XMLTools.encodeToFile(l, "MaListeDeProjets.xml");
    }
}
