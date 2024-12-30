package ec.edu.espe.model;

import java.util.Date;

/**
 *
 * @author Dennis Paucar
 */
public class ProjectChangeLog {
    private String projectId;
    private String projectTitle;
    private double oldQuote;
    private double newQuote;
    private Date changeDate;

    public ProjectChangeLog(String projectId, String projectTitle, double oldQuote, double newQuote, Date changeDate) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.oldQuote = oldQuote;
        this.newQuote = newQuote;
        this.changeDate = changeDate;
    }
      

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public double getOldQuote() {
        return oldQuote;
    }

    public void setOldQuote(double oldQuote) {
        this.oldQuote = oldQuote;
    }

    public double getNewQuote() {
        return newQuote;
    }

    public void setNewQuote(double newQuote) {
        this.newQuote = newQuote;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }
    
    
    
}
