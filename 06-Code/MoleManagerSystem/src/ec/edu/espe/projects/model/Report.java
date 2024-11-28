
package ec.edu.espe.projects.model;

/**
 *
 * @author Dennis Paucar
 */
public class Report {
    private String reportId;
    private String[] projectNames;
    private String[] projectDescriptions;
    private String[] rucs;
    private Status[] finalProjectStatus;
    private boolean[] paidStatus;
    private float[] quoteList;
    private boolean isDailyReport;
    private boolean isWeeklyReport;
    private boolean isMothlyReport;
    private boolean isQuarterReport;
    private float totalQuote;
    private Date[] endProjectDates;


    /**
     * @param reportId the reportId to set
     */
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

 

    /**
     * @param projectNames the projectNames to set
     */
    public void setProjectNames(String[] projectNames) {
        this.projectNames = projectNames;
    }

 

    /**
     * @param projectDescriptions the projectDescriptions to set
     */
    public void setProjectDescriptions(String[] projectDescriptions) {
        this.projectDescriptions = projectDescriptions;
    }

 

    /**
     * @param rucs the rucs to set
     */
    public void setRucs(String[] rucs) {
        this.rucs = rucs;
    }


    /**
     * @param finalProjectStatus the finalProjectStatus to set
     */
    public void doFinalProjectStatus(Status[] finalProjectStatus) {
        this.finalProjectStatus = finalProjectStatus;
    }

    /**
     * @return the quoteList
     */
    public float[] doQuoteList() {
        return quoteList;
    }


    /**
     * @return the isDailyReport
     */
    public boolean setTypeOfReport() {
        return isDailyReport;
    }
    

    /**
     * @return the totalQuote
     */
    public float calculateTotalQuote() {
        return totalQuote;
    }


    /**
     * @return the endProjectDates
     */
    public Date[] getEndProjectDates() {
        return endProjectDates;
    }

  
   
    
    }
