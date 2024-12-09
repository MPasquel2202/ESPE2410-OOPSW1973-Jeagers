package ec.edu.espe.mole.model;

import java.util.Date;

/**
 *
 * @author Dennis Paucar
 */

public class Support {
    private String supportId;
    private String relatedProjectId;
    private Date startDate;
    private Date endDate;
    private String status;

    public Support(String supportId, String relatedProjectId, Date startDate, Date endDate, String status) {
        this.supportId = supportId;
        this.relatedProjectId = relatedProjectId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getSupportId() {
        return supportId;
    }

    public void setSupportId(String supportId) {
        this.supportId = supportId;
    }

    public String getRelatedProjectId() {
        return relatedProjectId;
    }

    public void setRelatedProjectId(String relatedProjectId) {
        this.relatedProjectId = relatedProjectId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public boolean isSupportNearEnd(int daysBefore) {
        if (endDate == null) return false;
        long currentTime = new Date().getTime();
        long endTime = endDate.getTime();
        long daysDifference = (endTime - currentTime) / (1000 * 60 * 60 * 24);
        return daysDifference <= daysBefore;
    }

    @Override
    public String toString() {
        return "Support ID: " + supportId + "\n" +
               "Related Project ID: " + relatedProjectId + "\n" +
               "Start Date: " + startDate + "\n" +
               "End Date: " + endDate + "\n" +
               "Status: " + status;
    }
}
