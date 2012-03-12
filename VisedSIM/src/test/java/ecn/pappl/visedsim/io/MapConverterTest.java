/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.io;

import ecn.pappl.visedsim.struct.Project;
import ecn.pappl.visedsim.struct.ProjectList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author Denis
 */
public class MapConverterTest extends TestCase {

    public MapConverterTest(String testName) {
        super(testName);
    }

//    /**
//     * Test of convertMapToProject method, of class MapConverter.
//     */
//    public void testConvertMapToProject() throws Exception {
//        System.out.println("convertMapToProject");
//        int i = 0;
//
//        Map<Integer, String> map = new HashMap<Integer, String>();
//        map.put(i, "acronym");
//        i++;
//        map.put(i, "title");
//        i++;
//        map.put(i, "thematic");
//        i++;
//        map.put(i, "thematicSubThemes");
//        i++;
//        map.put(i, "thematicOtherThemes");
//        i++;
//        map.put(i, "appKeywords");
//        i++;
//        map.put(i, "projectKeywords");
//        i++;
//        map.put(i, "rdClass");
//        i++;
//        map.put(i, "summary");
//        i++;
//        map.put(i, "duration");
//        i++;
//        map.put(i, "mainDiscipline");
//        i++;
//        map.put(i, "partnershipProject");
//        i++;
//        map.put(i, "submittedProject");
//        i++;
//        map.put(i, "multidisciplinary");
//        i++;
//        map.put(i, "secondaryCommittee");
//        i++;
//        map.put(i, "secondaryCommitteeKeywords");
//        i++;
//        map.put(i, "internationalCooperation");
//        i++;
//        map.put(i, "transnationalProject");
//        i++;
//        map.put(i, "admissibility");
//        i++;
//        map.put(i, "eligibility");
//        i++;
//        map.put(i, "filledPreviousEdition");
//        i++;
//        map.put(i, "filingYear");
//        i++;
//        map.put(i, "previousProjectAcronym");
//        i++;
//        map.put(i, "followingProjectPreviouslyFunded");
//        i++;
//        map.put(i, "previousFundedProjectAcronym");
//        i++;
//        map.put(i, "involvementTime");
//        i++;
//        map.put(i, "coordinatorMail");
//        i++;
//        map.put(i, "competitivePoles");
//        i++;
//        map.put(i, "permanentCDD");
//        i++;
//        map.put(i, "anrFundedNonPermanentCDD");
//        i++;
//        map.put(i, "nonPermanentCDD");
//        i++;
//        map.put(i, "fullCost");
//        i++;
//        map.put(i, "requestedFunding");
//        i++;
//        map.put(i, "requestedEffort");
//        i++;
//        map.put(i, "unwantedExperts");
//        i++;
//        map.put(i, "suggestedExperts");
//        i++;
//
//        Map<String, List<Integer>> columnsOrder = new HashMap<String, List<Integer>>();
//
//        i = 0;
//        List<Integer> listInt = new LinkedList<Integer>();
//        listInt.add(0, i);
//
//        columnsOrder.put("Acronym", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("Title", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("Thematic", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("ThematicSubThemes", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("ThematicOtherThemes", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("AppKeywords", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("ProjectKeywords", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("RdClass", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("Summary", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("Duration", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("MainDiscipline", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("PartnershipProject", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("SubmittedProject", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("Multidisciplinary", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("SecondaryCommittee", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("SecondaryCommitteeKeywords", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("InternationalCooperation", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("TransnationalProject", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("Admissibility", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("Eligibility", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("FilledPreviousEdition", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("FilingYear", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("PreviousProjectAcronym", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("FollowingProjectPreviouslyFunded", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("PreviousFundedProjectAcronym", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("InvolvementTime", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("CoordinatorMail", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("CompetitivePoles", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("PermanentCDD", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("AnrFundedNonPermanentCDD", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("NonPermanentCDD", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("FullCost", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("RequestedFunding", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("RequestedEffort", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("UnwantedExperts", listInt);
//        i++;
//        listInt.set(0, i);
//        columnsOrder.put("SuggestedExperts", listInt);
//        i++;
//        listInt.set(0, i);
//
//        Project result = MapConverter.convertMapToProject(map, columnsOrder);
//
//        Project expResult = new Project();
//        expResult.setAcronym("acronyme");
//        expResult.setTitle("title");
//        expResult.setThematic("thematic");
//        expResult.setThematicSubThemes("thematicSubThemes");
//        expResult.setThematicOtherThemes("thematicOtherThemes");
//        expResult.setAppKeywords("appKeywords");
//        expResult.setProjectKeywords("projectKeywords");
//        expResult.setRdClass("rdClass");
//        expResult.setSummary("summary");
//        expResult.setDuration("duration");
//        expResult.setMainDiscipline("mainDiscipline");
//        expResult.setPartnershipProject("partnershipProject");
//        expResult.setSubmittedProject("submittedProject");
//        expResult.setMultidisciplinary("multidisciplinary");
//        expResult.setSecondaryCommittee("secondaryCommittee");
//        expResult.setSecondaryCommitteeKeywords("secondaryCommitteeKeywords");
//        expResult.setInternationalCooperation("internationalCooperation");
//        expResult.setTransnationalProject("transnationalProject");
//        expResult.setAdmissibility("admissibility");
//        expResult.setEligibility("eligibility");
//        expResult.setFilledPreviousEdition("filledPreviousEdition");
//        expResult.setFilingYear("filingYear");
//        expResult.setPreviousProjectAcronym("previousProjectAcronym");
//        expResult.setFollowingProjectPreviouslyFunded("followingProjectPreviouslyFunded");
//        expResult.setPreviousFundedProjectAcronym("previousFundedProjectAcronym");
//        expResult.setInvolvementTime("involvementTime");
//        expResult.setCoordinatorMail("coordinatorMail");
//
//        ArrayList<String> listePoles = new ArrayList<String>();
//        listePoles.add("competitivePoles");
//        expResult.setCompetitivePoles(listePoles);
//
//        expResult.setPermanentCDD("permanentCDD");
//        expResult.setAnrFundedNonPermanentCDD("anrFundedNonPermanentCDD");
//        expResult.setNonPermanentCDD("nonPermanentCDD");
//        expResult.setFullCost("fullCost");
//        expResult.setRequestedFunding("requestedFunding");
//        expResult.setRequestedEffort("requestedEffort");
//        expResult.setUnwantedExperts("unwantedExperts");
//
//        ArrayList<String> listexperts = new ArrayList<String>();
//        listexperts.add("suggestedExperts");
//        expResult.setSuggestedExperts(listexperts);
//
//        assertEquals("Les deux projets sont identiques", expResult, result);
//    }

    /**
     * Test of convertMapToProjectList method, of class MapConverter.
     */
    public void testConvertMapToProjectList() throws Exception {
        System.out.println("convertMapToProjectList");
        Map<Integer, Map<Integer, String>> map = null;
        Map<String, List<Integer>> columnsOrder = null;
        ProjectList expResult = null;
        ProjectList result = MapConverter.convertMapToProjectList(map, columnsOrder);
        assertEquals("Les deux listes de projets sont identiques", expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
