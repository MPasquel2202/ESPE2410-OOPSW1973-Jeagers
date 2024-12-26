package ec.edu.espe.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dennis Paucar
 */
public class Project {
    private String projectId;
    private String projectTitle;
    private String projectDescription;
    private Customer customer;
    private Date startDate;
    private Date closingDate;
    private double startquote;
    private ProjectStatus operationalStatus;
    private ProjectStatus quoteStatus;
    private boolean paid;
    private boolean invoiced;
    private boolean isPublic;

    public Project(String projectTitle, String projectId, String projectDescription, Customer customer, Date startDate, Date closingDate,
                   double startquote, ProjectStatus operationalStatus, ProjectStatus quoteStatus, boolean paid,
                   boolean invoiced, boolean isPublic) {
        this.projectTitle = projectTitle;
        this.projectId = projectId;
        this.projectDescription = projectDescription;
        this.customer = customer;
        this.startDate = startDate;
        this.closingDate = closingDate;
        this.startquote = startquote;
        this.operationalStatus = operationalStatus;
        this.quoteStatus = quoteStatus;
        this.paid = paid;
        this.invoiced = invoiced;
        this.isPublic = isPublic;
    }
    
    public void displayProjectData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

     
        System.out.println("+--------------------------------------------------------------+");
        System.out.printf("| %-60s |\n", "Detalles del Proyecto");
        System.out.println("+--------------------------------------------------------------+");

       
        System.out.printf("| %-18s | %-45s |\n", "ID Proyecto:", projectId);
        System.out.printf("| %-18s | %-45s |\n", "Titulo:", projectTitle);
        System.out.printf("| %-18s | %-45s |\n", "Descripcion:", projectDescription);
        System.out.println("+--------------------------------------------------------------+");

       
        System.out.printf("| %-18s | %-45s |\n", "Cliente:", customer != null ? customer.toString() : "Sin definir");
        System.out.println("+--------------------------------------------------------------+");

        
        System.out.printf("| %-18s | %-45s |\n", "Fecha de Inicio:", startDate != null ? dateFormat.format(startDate) : "No definida");
        System.out.printf("| %-18s | %-45s |\n", "Fecha de Cierre:", closingDate != null ? dateFormat.format(closingDate) : "No definida");
        System.out.println("+--------------------------------------------------------------+");

        
        System.out.printf("| %-18s | %-45.2f |\n", "Presupuesto:", startquote);
        System.out.printf("| %-18s | %-45s |\n", "Estado Operativo:", operationalStatus);
        System.out.printf("| %-18s | %-45s |\n", "Estado Cotizacion:", quoteStatus);
        System.out.println("+--------------------------------------------------------------+");

        
        System.out.printf("| %-18s | %-45s |\n", "Facturado:", invoiced ? "Si" : "No");
        System.out.printf("| %-18s | %-45s |\n", "Pagado:", paid ? "Si" : "No");
        System.out.printf("| %-18s | %-45s |\n", "Es Publico:", isPublic ? "Si" : "No");
        System.out.println("+--------------------------------------------------------------+");
    }

    /**
     * @return the projectTitle
     */
    public String getProjectTitle() {
        return projectTitle;
    }

    /**
     * @param projectTitle the projectTitle to set
     */
    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }
    
    public String getProjectId(){
        return projectId;
    }

    /**
     * @return the projectDescription
     */
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * @param projectDescription the projectDescription to set
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the closingDate
     */
    public Date getClosingDate() {
        return closingDate;
    }

    /**
     * @param closingDate the closingDate to set
     */
    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    /**
     * @return the startquote
     */
    public double getStartquote() {
        return startquote;
    }

    /**
     * @param startquote the startquote to set
     */
    public void setStartquote(double startquote) {
        this.startquote = startquote;
    }

    /**
     * @return the operationalStatus
     */
    public ProjectStatus getOperationalStatus() {
        return operationalStatus;
    }

    /**
     * @param operationalStatus the operationalStatus to set
     */
    public void setOperationalStatus(ProjectStatus operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    /**
     * @return the quoteStatus
     */
    public ProjectStatus getQuoteStatus() {
        return quoteStatus;
    }

    /**
     * @param quoteStatus the quoteStatus to set
     */
    public void setQuoteStatus(ProjectStatus quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    /**
     * @return the paid
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * @param paid the paid to set
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /**
     * @return the invoiced
     */
    public boolean isInvoiced() {
        return invoiced;
    }

    /**
     * @param invoiced the invoiced to set
     */
    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    /**
     * @return the isPublic
     */
    public boolean isIsPublic() {
        return isPublic;
    }

    /**
     * @param isPublic the isPublic to set
     */
    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
    
}
