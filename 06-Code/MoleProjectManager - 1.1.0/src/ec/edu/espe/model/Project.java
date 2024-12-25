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
    private Date startDate;
    private Date closingDate;
    private double startquote;
    private ProjectStatus operationalStatus;
    private ProjectStatus quoteStatus;
    private boolean paid;
    private boolean invoiced;
    private boolean isPublic;

    public Project(String projectTitle, String projectId, String projectDescription, Date startDate, Date closingDate,
                   double startquote, ProjectStatus operationalStatus, ProjectStatus quoteStatus, boolean paid,
                   boolean invoiced, boolean isPublic) {
        this.projectTitle = projectTitle;
        this.projectId = projectId;
        this.projectDescription = projectDescription;
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

  
    String separator = "+------------------------------------------------------------+";
    String titleHeader = "| %-60s |";
    String dataHeader = "| %-15s %-45s |";

   
    System.out.println(separator);
    System.out.printf(titleHeader, "Proyecto Detalles");
    System.out.println("\n" + separator);


    System.out.println("+--------------------+--------------------------------------------------+");
    System.out.printf("| %-18s | %-48s |\n", "ID Proyecto:", projectId);
    System.out.println("+--------------------+--------------------------------------------------+");
    System.out.printf("| %-18s | %-48s |\n", "Titulo:", projectTitle);
    System.out.printf("| %-18s | %-48s |\n", "Descripcion:", projectDescription);
    

    System.out.printf("| %-18s | %-48s |\n", "Fecha de Inicio:", startDate != null ? dateFormat.format(startDate) : "No definida");
    System.out.printf("| %-18s | %-48s |\n", "Fecha de Cierre:", closingDate != null ? dateFormat.format(closingDate) : "No definida");

  
    System.out.printf("| %-18s | %-48.2f |\n", "Presupuesto Inicial:", startquote);
    System.out.printf("| %-18s | %-48s |\n", "Estado Operativo:", operationalStatus);
    System.out.printf("| %-18s | %-48s |\n", "Estado Cotizacion:", quoteStatus);
    System.out.printf("| %-18s | %-48b |\n", "Facturado:", invoiced);
    System.out.printf("| %-18s | %-48b |\n", "Pagado:", paid);
    System.out.printf("| %-18s | %-48b |\n", "Es Publico:", isPublic);
    System.out.println("+--------------------+--------------------------------------------------+");


    System.out.println(separator);
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
