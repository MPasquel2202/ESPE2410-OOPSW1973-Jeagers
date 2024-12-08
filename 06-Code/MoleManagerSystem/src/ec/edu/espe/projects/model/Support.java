package ec.edu.espe.projects.model;

/**
 *
 * @author David Pilatasig
 */
public class Support {
    private String suportID;
    private String projectID;
    private Date startDate;
    private Date endDate;
    private String status;

    public Support() {
    }

    public Support(String suportID, String projectID, Date startDate, Date endDate, String status) {
        this.suportID = suportID;
        this.projectID = projectID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuportID() {
        return suportID;
    }

    public void setSuportID(String suportID) {
        this.suportID = suportID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
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

    public void updateStatus(String status){
        
    }
    
    public boolean isSupportNearEnd(){
        return false;
    }
}
