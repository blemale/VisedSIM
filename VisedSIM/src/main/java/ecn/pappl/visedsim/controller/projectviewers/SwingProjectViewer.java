/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.projectviewers;

import ecn.pappl.visedsim.struct.CriteriaPreselection;

/**
 * Interface representing a {@link Project} viewer for Swing.
 * <p/>
 * @author bastien
 */
public interface SwingProjectViewer {

    /**
     * Get the acronym of the current {@link Project}.
     * <p/>
     * @return a {@link String} representing the acronym of the current {@link Project}
     */
    String getAcronym();

    /**
     * Get the title of the current {@link Project}.
     * <p/>
     * @return a {@link String} representing the title of the current {@link Project}.
     */
    String getTitle();

    /**
     * Get the selected criteria of the current {@link Project} in a two
     * dimensions {@link Array} of {@link Object}.
     * <p/>
     * @param criteriaPreselection the selection of criteria.
     * @return a two dimensions {@link Array} of {@link Object} with in the
     * first column the names of the criteria and in the second the value of the
     * given criteria for the given {@link Project}.
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    Object[][] getCriteria(CriteriaPreselection criteriaPreselection) throws
            IllegalArgumentException, IllegalAccessException;
}
