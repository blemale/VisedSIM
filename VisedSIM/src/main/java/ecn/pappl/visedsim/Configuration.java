/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim;

import java.io.File;

/**
 * Class representing the configuration of the application
 * <p/>
 * @author bastien
 */
public final class Configuration {

    /**
     * Is the admin or user application.
     */
    public static final Boolean IS_ADMIN = true;
    /**
     * Path of the user's home folder.
     */
    public static final String USER_HOME_FOLDER =
            System.getProperty("user.home");
    /**
     * Path of the VisedSim folder
     */
    public static final String VISEDSIM_FOLDER = USER_HOME_FOLDER
            + File.separator + ".VisedSIM";
    /**
     * Path of the criteria preselection folder
     */
    public static final String CRITERIA_PRESELECTION_FOLDER = VISEDSIM_FOLDER
            + File.separator + "criteria_preselections";
    /**
     * Path of the default class instances.
     */
    public static final String DEFAULT_INSTANCES_FOLDER = "default_instances";
    /**
     * Path of the default columns order.
     */
    public static final String COLUMNS_ORDER_FILE_PATH =
            DEFAULT_INSTANCES_FOLDER + "/default_columns_order";
    /**
     * Path of the default criteria preselection.
     */
    public static final String DEFAULT_CRITERIA_PRESELECTION_PATH = DEFAULT_INSTANCES_FOLDER
            + "/default_criteria_preselection";
    /**
     * Path of the initial criteria preselection.
     */
    public static final String INIT_CRITERIA_PRESELECTION_PATH = DEFAULT_INSTANCES_FOLDER
            + "/init_criteria_preselection";
    /**
     * Path of the i18n folder.
     */
    public static final String I18N_FOLDER = "i18n";
    
    public static final String ACRONYM = "acronym";
    public static final String TITLE = "title";

    private Configuration() {
    }
}
