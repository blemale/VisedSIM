/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.criteriapreselection;

import ecn.pappl.visedsim.Configuration;
import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.utilities.FileTools;
import ecn.pappl.visedsim.utilities.XMLPersistanceTools;
import java.io.*;
import java.util.List;

/**
 * Class representing a controller for a {@link CriteriaPreselection}
 *
 * @author bastien
 */
public final class CriteriaPreselectionController implements
        CriteriaPreselectionFactory, CriteriaPreselectionLoader,
        CriteriaPreselectionSaver {

    /**
     * Field representing the current {@link CriteriaPreselection}
     */
    private CriteriaPreselection criteriaPreselection = null;
    /**
     * Unique instace of {@link CriteriaPreselectionController}.
     */
    private static volatile CriteriaPreselectionController instance = null;

    /**
     * Default constructor.
     */
    private CriteriaPreselectionController() {
        super();
    }

    /**
     * Get the unique instance of {@link CriteriaPreselectionController}.
     * <p/>
     * @return the unique instance of {@link CriteriaPreselectionController}
     */
    public static CriteriaPreselectionController getInstance() {
        if (CriteriaPreselectionController.instance == null) {
            synchronized (CriteriaPreselectionController.class) {
                CriteriaPreselectionController.instance =
                        new CriteriaPreselectionController();
            }
        }
        return CriteriaPreselectionController.instance;
    }

    public CriteriaPreselection initCriteriaPreselection() throws
            FileNotFoundException, IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(
                Configuration.INIT_CRITERIA_PRESELECTION_PATH);
        this.criteriaPreselection = (CriteriaPreselection) XMLPersistanceTools.
                decodeFromFile(is);
        return this.criteriaPreselection;
    }

    public CriteriaPreselection createCriteriaPreselection() throws
            FileNotFoundException, IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(
                Configuration.DEFAULT_CRITERIA_PRESELECTION_PATH);
        return (CriteriaPreselection) XMLPersistanceTools.decodeFromFile(is);
    }

    public void deleteCriteriaPreselection(final String fileName) {
        String path = getClass().getClassLoader().getResource(
                Configuration.CRITERIA_PRESELECTION_FOLDER).getPath();
        File directory = new File(path);
        File[] fileToDelete = directory.listFiles(new FilenameFilter() {

            public boolean accept(File file, String string) {
                return string.equals(fileName);
            }
        });
        if (fileToDelete.length == 1) {
            fileToDelete[0].delete();
        }
    }

    public List<String> getLoadableCriteriaPreselectionsNames() {
        String path = getClass().getClassLoader().getResource(
                Configuration.CRITERIA_PRESELECTION_FOLDER).getPath();
        return FileTools.getFilesNamesInDirectory(path);
    }

    public CriteriaPreselection loadCriteriaPreselection(final String fileName) throws
            FileNotFoundException, IOException {
        String path = getClass().getClassLoader().getResource(
                Configuration.CRITERIA_PRESELECTION_FOLDER).getPath();
        File directory = new File(path);
        File[] file = directory.listFiles(new FilenameFilter() {

            public boolean accept(File file, String string) {
                return string.equals(fileName);
            }
        });
        CriteriaPreselection loadedCriteriaPreselection = new CriteriaPreselection();
        if (file.length == 1) {
            loadedCriteriaPreselection = (CriteriaPreselection) XMLPersistanceTools.
                    decodeFromFile(file[0].getPath());
        }

        return loadedCriteriaPreselection;
    }

    public void saveCriteriaPreselection(String fileName) throws
            FileNotFoundException, IOException {
        if (this.criteriaPreselection != null) {
            String path = getClass().getClassLoader().getResource(
                Configuration.CRITERIA_PRESELECTION_FOLDER).getPath();
            String filePath = path+File.separator+fileName;
            XMLPersistanceTools.encodeToFile(this.criteriaPreselection, filePath);
        }
    }

    public CriteriaPreselection getCriteriaPreselection() {
        return criteriaPreselection;
    }

    public void setCriteriaPreselection(
            CriteriaPreselection criteriaPreselection) {
        this.criteriaPreselection = criteriaPreselection;
    }
}
