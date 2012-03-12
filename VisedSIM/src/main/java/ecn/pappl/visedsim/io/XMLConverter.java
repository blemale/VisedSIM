/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.io;

import ecn.pappl.visedsim.Configuration;
import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.struct.ProjectList;
import ecn.pappl.visedsim.utilities.ProjectTools;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.jdom.Document;
import org.jdom.Element;

/**
 *
 * @author bastien
 */
public class XMLConverter {

    public static Document convertProjectToXML(Project project) {
        Element xmlRoot = new Element("Project");
        Document xmlDocument = new Document(xmlRoot);

        XMLConverter.addProjectToXMLDocument(project, xmlDocument, xmlRoot);

        return xmlDocument;
    }

    public static Document convertProjectToXMLWithCriteriaPreselection(
            Project project, CriteriaPreselection criteriaPreselection) {
        Project newProject = ProjectTools.applyCriteriaPreselection(project,
                criteriaPreselection);
        return XMLConverter.convertProjectToXML(newProject);
    }

    public static Document convertProjectListToXML(ProjectList projectList) {
        Element xmlRoot = new Element("ProjectList");
        Document xmlDocument = new Document(xmlRoot);
        
        List<Project> projects = projectList.getProjectList();
        for(Project project : projects){
            Element xmlProject = new Element("Project");
            XMLConverter.addProjectToXMLDocument(project, xmlDocument,
                    xmlProject);
        }
        return xmlDocument;
    }

    public static Document convertProjectListToXMLWithCriteriaPreselection(
            ProjectList projectList, CriteriaPreselection criteriaPreselection) {
        Element xmlRoot = new Element("ProjectList");
        Document xmlDocument = new Document(xmlRoot);
        
        List<Project> projects = projectList.getProjectList();
        for(Project project : projects){
            Project newProject = ProjectTools.applyCriteriaPreselection(project,
                    criteriaPreselection);
            Element xmlProject = new Element("Project");
            XMLConverter.addProjectToXMLDocument(newProject, xmlDocument,
                    xmlProject);
        }
        return xmlDocument;
    }
    
    public static void addProjectToXMLDocument(Project project, Document xmlDocument, Element xmlParentElement){
         String path = Configuration.I18N_FOLDER + File.separator
                + "Criteria";
        ResourceBundle bundle = ResourceBundle.getBundle(path,
                Locale.getDefault());
        
        Map<String, List<String>> criteriaMap = project.getCriteriaMap();
        for (String criteria : criteriaMap.keySet()) {
            Element xmlCriteria = new Element(criteria);
            xmlParentElement.addContent(xmlCriteria);
            Element xmlCriteriaName = new Element("name");
            xmlCriteriaName.setText(bundle.getString(criteria));
            xmlCriteria.addContent(xmlCriteriaName);

            Element xmlCriteriaValues = new Element("values");
            for (String value : criteriaMap.get(criteria)) {
                Element xmlCriteriaValue = new Element("value");
                xmlCriteriaValue.setText(value);
                xmlCriteriaValues.addContent(xmlCriteriaValue);
            }
            xmlCriteria.addContent(xmlCriteriaValues);      
        }       
    }
}
