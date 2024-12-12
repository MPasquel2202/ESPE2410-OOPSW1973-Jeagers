package ec.edu.espe.mole.model;

import java.util.Date;

/**
 *
 * @author Marlon Pasquel
 */

public class Project {
    private String projectId;
    private String description;
    private Status status;
    private Customer customer;
    private float quation;
    private Date startDate;
    private Date endDate;
    private Date supportEndDate;

    public Project(String projectId, String description, Status status, Customer customer, float quation, Date startDate) {
        this.projectId = projectId;
        this.description = description;
        this.status = status;
        this.customer = customer;
        this.quation = quation;
        this.startDate = startDate;
    }


    public String getProjectId() {
        return projectId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }


    public float getQuation() {
        return quation;
    }

    public void setQuation(float quation) {
        this.quation = quation;
    }

    public Date getStartDate() {
        return startDate;
    }


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getSupportEndDate() {
        return supportEndDate;
    }

    public void setSupportEndDate(Date supportEndDate) {
        this.supportEndDate = supportEndDate;
    }

    

//    public boolean isSupportEndingSoon(int daysBefore) {
//      if (supportEndDate == null) return false;
//        long currentTime = new Date().getTime();
//        long supportTime = supportEndDate.getTime();
//        long daysDifference = (supportTime - currentTime) / (1000 * 60 * 60 * 24);
//        return daysDifference <= daysBefore;
//    }

    @Override
    public String toString() {
        return "Project ID: " + projectId + "\n" +
               "Description: " + description + "\n" +
               "Status: " + status + "\n" +
               "Customer: " + customer.getCustomerInfo() + "\n" +
               "Quation in dollars: " + quation + "\n" +
               "Start Date: " + startDate + "\n" +
               (endDate != null ? "End Date: " + endDate + "\n" : "") +
               (supportEndDate != null ? "Support End Date: " + supportEndDate + "\n" : "");
    }
}


