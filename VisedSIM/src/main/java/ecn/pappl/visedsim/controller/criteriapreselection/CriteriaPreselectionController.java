/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.criteriapreselection;

import ecn.pappl.visedsim.Configuration;
import ecn.pappl.visedsim.struct.CriteriaPreselection;
import ecn.pappl.visedsim.utilities.FileTools;
import ecn.pappl.visedsim.utilities.XMLTools;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Class representing a controller for a {@link CriteriaPreselection}
 *
 * @author bastien
 */
public class CriteriaPreselectionController implements
        CriteriaPreselectionFactory, CriteriaPreselectionLoader,
        CriteriaPreselectionSaver {

    /**
     * Field representing the current {@link CriteriaPreselection}
     */
    private CriteriaPreselection criteriaPreselection = null;
    /**
     * Unique instace of {@link CriteriaPreselectionController}.
     */
    private volatile static CriteriaPreselectionController instance = null;

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
    public final static CriteriaPreselectionController getInstance() {
        if (CriteriaPreselectionController.instance == null) {
            synchronized (CriteriaPreselectionController.class) {
                CriteriaPreselectionController.instance =
                        new CriteriaPreselectionController();
            }
        }
        return CriteriaPreselectionController.instance;

    }

    public CriteriaPreselection createCriteriaPreselection() {
        this.criteriaPreselection = new CriteriaPreselection();
        return this.criteriaPreselection;
    }

    public List<String> getLoadableCriteriaPreselectionsNames() {
        return FileTools.getFilesNamesInDirectory(
                Configuration.CRITERIA_PRESELECTION_FOLDER);
    }

    public CriteriaPreselection loadCriteriaPreselection(String fileName) throws
            FileNotFoundException, IOException {
        this.criteriaPreselection = (CriteriaPreselection) XMLTools.
                decodeFromFile(fileName);
        return this.criteriaPreselection;
    }

    public void saveCriteriaPreselection(String fileName) throws
            FileNotFoundException, IOException {
        if (this.criteriaPreselection != null) {
            XMLTools.encodeToFile(this.criteriaPreselection, fileName);
        }
    }
}
