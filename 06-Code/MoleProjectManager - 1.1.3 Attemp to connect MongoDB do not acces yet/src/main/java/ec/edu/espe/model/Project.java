package ec.edu.espe.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.bson.Document;

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

    public Project() {
        this.projectTitle = "";
        this.projectId = "";
        this.projectDescription = "";
        this.customer = null;
        this.startDate = null;
        this.closingDate = null;
        this.startquote = 0.0;
        this.operationalStatus = null;
        this.quoteStatus = null;
        this.paid = false;
        this.invoiced = false;
        this.isPublic = false;
    }
    
    public Project(String projectId, String projectTitle, ProjectStatus operationalStatus) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.operationalStatus = operationalStatus;
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

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
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

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public double getStartquote() {
        return startquote;
    }

    public void setStartquote(double startquote) {
        this.startquote = startquote;
    }

    public ProjectStatus getOperationalStatus() {
        return operationalStatus;
    }

    public void setOperationalStatus(ProjectStatus operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    public ProjectStatus getQuoteStatus() {
        return quoteStatus;
    }

    public void setQuoteStatus(ProjectStatus quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isInvoiced() {
        return invoiced;
    }

    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    public boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
    
    @Override
    public String toString() {
        return new Document("projectId", projectId)
                .append("projectTitle", projectTitle)
                .append("projectDescription", projectDescription)
                .append("customer", customer != null ? Document.parse(customer.toString()) : null)
                .append("startDate", startDate != null ? new SimpleDateFormat("yyyy-MM-dd").format(startDate) : null)
                .append("closingDate", closingDate != null ? new SimpleDateFormat("yyyy-MM-dd").format(closingDate) : null)
                .append("startquote", startquote)
                .append("operationalStatus", operationalStatus != null ? operationalStatus.toString() : null)
                .append("quoteStatus", quoteStatus != null ? quoteStatus.toString() : null)
                .append("paid", paid)
                .append("invoiced", invoiced)
                .append("isPublic", isPublic)
                .toJson();
    }
    
}
