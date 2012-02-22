/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.controller.projectlist;

import ecn.pappl.visedsim.struct.Project;
import java.util.List;

/**
 * Interface representing a {@link Project} selector in a {@link ProjectList}.
 *
 * @author bastien
 */
public interface ProjectListSelector {

    /**
     * Gets the {@link List} of the acronyms of the {@link Project}s contained
     * in the current {@link ProjectList}.
     *
     * @return a {@link List} of {@link String} representing the {@link Project}'s
     * acronyms.
     */
    List<String> getProjectsAcronyms();

    /**
     * Gets the {@link List} of the acronyms of the {@link Project}s contained
     * in the current {@link ProjectList} which match with given first letters.
     *
     * @param firstLetters the substring to match with the {@link Project}'s
     * acronym.
     * @return a {@link List} of {@link String} representing the {@link Project}'s
     * acronyms which match.
     */
    List<String> getProjectsAcronymsByFirstLetters(String firstLetters);

    /**
     * Gets a given {@link Project} in the current {@link ProjectList} by its
     * acronym.
     *
     * @param acronym the acronym of the project to get.
     * @return the {@link Project} with the given acronym or null if no {@link Project}
     * is found.
     */
    Project getProjectByAcronym(String acronym);
}
