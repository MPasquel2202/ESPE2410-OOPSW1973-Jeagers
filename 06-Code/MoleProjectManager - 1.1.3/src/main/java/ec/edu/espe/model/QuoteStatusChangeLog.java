package ec.edu.espe.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.bson.Document;

/**
 *
 * @author Dennis Paucar
 */
public class QuoteStatusChangeLog {
    private String projectId;
    private String projectTitle;
    private String oldQuoteStatus;
    private String newQuoteStatus;
    private Date changeDate;

    public QuoteStatusChangeLog(String projectId, String projectTitle, String oldQuoteStatus, String newQuoteStatus, Date changeDate) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.oldQuoteStatus = oldQuoteStatus;
        this.newQuoteStatus = newQuoteStatus;
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

    public String getOldQuoteStatus() {
        return oldQuoteStatus;
    }

    public void setOldQuoteStatus(String oldQuoteStatus) {
        this.oldQuoteStatus = oldQuoteStatus;
    }

    public String getNewQuoteStatus() {
        return newQuoteStatus;
    }

    public void setNewQuoteStatus(String newQuoteStatus) {
        this.newQuoteStatus = newQuoteStatus;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }
   
    @Override
public String toString() {
    return new Document("projectId", projectId)
            .append("projectTitle", projectTitle)
            .append("oldQuoteStatus", oldQuoteStatus)
            .append("newQuoteStatus", newQuoteStatus)
            .append("changeDate", changeDate != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(changeDate) : null)
            .toJson();
}

    
}
