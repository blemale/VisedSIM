/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.struct;

import java.util.ArrayList;

/**
 *
 * @author bastien
 */
public class Project {
    private String acronym;
    private String title;
    private String thematic;
    private String thematicSubThemes;
    private String thematicOtherThemes;
    private String appKeywords;
    private String projectKeywords;
    private String rdClass;
    private String summary;
    private String duration;
    private String mainDiscipline;
    private String partnershipProject;
    private String submittedProject;
    private String multidisciplinary;
    private String secondaryCommittee;
    private String secondaryCommitteeKeywords;
    private String internationalCooperation;
    private String transnationalProject;
    private String admissibility;
    private String eligibility;
    private String filledPreviousEdition;
    private String filingYear;
    private String previousProjectAcronym;
    private String followingProjectPreviouslyFunded;
    private String previousFundedProjectAcronym;
    private String involvementTime;
    private String coordinatorMail;
    private ArrayList<String> competitivePoles;
    private String permanentCDD;
    private String anrFundedNonPermanentCDD;
    private String nonPermanentCDD;
    private String fullCost;
    private String requestedFunding;
    private String unwantedExperts;
    private ArrayList<String> suggestedExperts;
    
    public Project(){
        super();
    }
    
    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getAdmissibility() {
        return admissibility;
    }

    public void setAdmissibility(String admissibility) {
        this.admissibility = admissibility;
    }

    public String getAnrFundedNonPermanentCDD() {
        return anrFundedNonPermanentCDD;
    }

    public void setAnrFundedNonPermanentCDD(String anrFundedNonPermanentCDD) {
        this.anrFundedNonPermanentCDD = anrFundedNonPermanentCDD;
    }

    public String getAppKeywords() {
        return appKeywords;
    }

    public void setAppKeywords(String appKeywords) {
        this.appKeywords = appKeywords;
    }

    public ArrayList<String> getCompetitivePoles() {
        return competitivePoles;
    }

    public void setCompetitivePoles(ArrayList<String> competitivePoles) {
        this.competitivePoles = competitivePoles;
    }

    public String getCoordinatorMail() {
        return coordinatorMail;
    }

    public void setCoordinatorMail(String coordinatorMail) {
        this.coordinatorMail = coordinatorMail;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getFilingYear() {
        return filingYear;
    }

    public void setFilingYear(String filingYear) {
        this.filingYear = filingYear;
    }

    public String getFilledPreviousEdition() {
        return filledPreviousEdition;
    }

    public void setFilledPreviousEdition(String filledPreviousEdition) {
        this.filledPreviousEdition = filledPreviousEdition;
    }

    public String getFollowingProjectPreviouslyFunded() {
        return followingProjectPreviouslyFunded;
    }

    public void setFollowingProjectPreviouslyFunded(String followingProjectPreviouslyFunded) {
        this.followingProjectPreviouslyFunded = followingProjectPreviouslyFunded;
    }

    public String getFullCost() {
        return fullCost;
    }

    public void setFullCost(String fullCost) {
        this.fullCost = fullCost;
    }

    public String getInternationalCooperation() {
        return internationalCooperation;
    }

    public void setInternationalCooperation(String internationalCooperation) {
        this.internationalCooperation = internationalCooperation;
    }

    public String getInvolvementTime() {
        return involvementTime;
    }

    public void setInvolvementTime(String involvementTime) {
        this.involvementTime = involvementTime;
    }

    public String getMainDiscipline() {
        return mainDiscipline;
    }

    public void setMainDiscipline(String mainDiscipline) {
        this.mainDiscipline = mainDiscipline;
    }

    public String getMultidisciplinary() {
        return multidisciplinary;
    }

    public void setMultidisciplinary(String multidisciplinary) {
        this.multidisciplinary = multidisciplinary;
    }

    public String getNonPermanentCDD() {
        return nonPermanentCDD;
    }

    public void setNonPermanentCDD(String nonPermanentCDD) {
        this.nonPermanentCDD = nonPermanentCDD;
    }

    public String getPartnershipProject() {
        return partnershipProject;
    }

    public void setPartnershipProject(String partnershipProject) {
        this.partnershipProject = partnershipProject;
    }

    public String getPermanentCDD() {
        return permanentCDD;
    }

    public void setPermanentCDD(String permanentCDD) {
        this.permanentCDD = permanentCDD;
    }

    public String getPreviousFundedProjectAcronym() {
        return previousFundedProjectAcronym;
    }

    public void setPreviousFundedProjectAcronym(String previousFundedProjectAcronym) {
        this.previousFundedProjectAcronym = previousFundedProjectAcronym;
    }

    public String getPreviousProjectAcronym() {
        return previousProjectAcronym;
    }

    public void setPreviousProjectAcronym(String previousProjectAcronym) {
        this.previousProjectAcronym = previousProjectAcronym;
    }

    public String getProjectKeywords() {
        return projectKeywords;
    }

    public void setProjectKeywords(String projectKeywords) {
        this.projectKeywords = projectKeywords;
    }

    public String getRdClass() {
        return rdClass;
    }

    public void setRdClass(String rdClass) {
        this.rdClass = rdClass;
    }

    public String getRequestedFunding() {
        return requestedFunding;
    }

    public void setRequestedFunding(String requestedFunding) {
        this.requestedFunding = requestedFunding;
    }

    public String getSecondaryCommittee() {
        return secondaryCommittee;
    }

    public void setSecondaryCommittee(String secondaryCommittee) {
        this.secondaryCommittee = secondaryCommittee;
    }

    public String getSecondaryCommitteeKeywords() {
        return secondaryCommitteeKeywords;
    }

    public void setSecondaryCommitteeKeywords(String secondaryCommitteeKeywords) {
        this.secondaryCommitteeKeywords = secondaryCommitteeKeywords;
    }

    public String getSubmittedProject() {
        return submittedProject;
    }

    public void setSubmittedProject(String submittedProject) {
        this.submittedProject = submittedProject;
    }

    public ArrayList<String> getSuggestedExperts() {
        return suggestedExperts;
    }

    public void setSuggestedExperts(ArrayList<String> suggestedExperts) {
        this.suggestedExperts = suggestedExperts;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getThematic() {
        return thematic;
    }

    public void setThematic(String thematic) {
        this.thematic = thematic;
    }

    public String getThematicOtherThemes() {
        return thematicOtherThemes;
    }

    public void setThematicOtherThemes(String thematicOtherThemes) {
        this.thematicOtherThemes = thematicOtherThemes;
    }

    public String getThematicSubThemes() {
        return thematicSubThemes;
    }

    public void setThematicSubThemes(String thematicSubThemes) {
        this.thematicSubThemes = thematicSubThemes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTransnationalProject() {
        return transnationalProject;
    }

    public void setTransnationalProject(String transnationalProject) {
        this.transnationalProject = transnationalProject;
    }

    public String getUnwantedExperts() {
        return unwantedExperts;
    }

    public void setUnwantedExperts(String unwantedExperts) {
        this.unwantedExperts = unwantedExperts;
    }
    
}
