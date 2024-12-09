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
    private Date startDate;
    private Date endDate;
    private Date supportEndDate;

    public Project(String projectId, String description, Customer customer, Date startDate, Status status) {
        this.projectId = projectId;
        this.description = description;
        this.customer = customer;
        this.startDate = startDate;
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Date getSupportEndDate() {
        return supportEndDate;
    }

    public void setSupportEndDate(Date supportEndDate) {
        this.supportEndDate = supportEndDate;
    }

    public void updateStatus(Status newStatus) {
        this.status = newStatus;
    }

    public boolean isSupportEndingSoon(int daysBefore) {
        if (supportEndDate == null) return false;
        long currentTime = new Date().getTime();
        long supportTime = supportEndDate.getTime();
        long daysDifference = (supportTime - currentTime) / (1000 * 60 * 60 * 24);
        return daysDifference <= daysBefore;
    }

    @Override
    public String toString() {
        return "Project ID: " + projectId + "\n" +
               "Description: " + description + "\n" +
               "Status: " + status + "\n" +
               "Customer: " + customer.getCustomerInfo() + "\n" +
               "Start Date: " + startDate + "\n" +
               (endDate != null ? "End Date: " + endDate + "\n" : "") +
               (supportEndDate != null ? "Support End Date: " + supportEndDate + "\n" : "");
    }
}


