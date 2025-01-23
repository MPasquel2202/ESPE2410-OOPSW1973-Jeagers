package ec.edu.espe.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.bson.Document;

/**
 *
 * @author Dennis Paucar
 */
public class StatusChangeLog {
    private String projectId;
    private String projectTitle;
    private String oldStatus;
    private String newStatus;
    private Date changeDate;
    private String description;

    public StatusChangeLog(String projectId, String projectTitle, String oldStatus, String newStatus, Date changeDate) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
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

    public String getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(String oldSrarus) {
        this.oldStatus = oldSrarus;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
   @Override
public String toString() {
    return new Document("projectId", projectId)
            .append("projectTitle", projectTitle)
            .append("oldStatus", oldStatus)
            .append("newStatus", newStatus)
            .append("changeDate", changeDate != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(changeDate) : null)
            .append("description", description)
            .toJson();
} 

   
            
   
}
