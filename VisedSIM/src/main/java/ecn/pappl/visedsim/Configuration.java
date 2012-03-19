/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim;

import java.io.File;

/**
 *
 * @author bastien
 */
public final class Configuration {

    public static final Boolean IS_ADMIN = false;
    public static final String USER_HOME_FOLDER =
            System.getProperty("user.home");
    public static final String VISEDSIM_FOLDER = USER_HOME_FOLDER
            + File.separator + ".VisedSIM";
    public static final String CRITERIA_PRESELECTION_FOLDER = VISEDSIM_FOLDER
            + File.separator + "criteria_preselections";
    public static final String DEFAULT_INSTANCES_FOLDER = "default_instances";
    public static final String COLUMNS_ORDER_FILE_PATH =
            DEFAULT_INSTANCES_FOLDER + "/default_columns_order";
    public static final String DEFAULT_CRITERIA_PRESELECTION_PATH = DEFAULT_INSTANCES_FOLDER
            + "/default_criteria_preselection";
    public static final String INIT_CRITERIA_PRESELECTION_PATH = DEFAULT_INSTANCES_FOLDER
            + "/init_criteria_preselection";
    public static final String I18N_FOLDER = "i18n";

    private Configuration() {
    }
}
