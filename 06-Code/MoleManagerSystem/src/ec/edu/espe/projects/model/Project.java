package ec.edu.espe.projects.model;

/**
 *
 * @author Dennis Paucar
 */
public class Project {
    private String projectName;
    private String shortDescription;
    private Date projectStartDate;
    private Date projectFinalDate;
    private float quote;
    private String purchaseOrderDocument;
    private Customer customer;
    private Status projectStatus;
    private Notification statusNotification;

    


    /**
     * @return the shortDescription
     */
    public String doShortDescription() {
        return shortDescription;
    }

    
    /**
     * @return the projectStartDate
     */
    public Date getProjectStartDate() {
        return projectStartDate;
    }


    /**
     * @return the projectFinalDate
     */
    public Date getProjectFinalDate() {
        return projectFinalDate;
    }


    
    public String getPurchaseOrderDocument() {
        return purchaseOrderDocument;
    }


    /**
     * @return the projectStatus
     */
    public Status getProjectStatus() {
        return projectStatus;
    }

    
    public Notification getStatusNotification() {
        return statusNotification;
    }

    
    
}
