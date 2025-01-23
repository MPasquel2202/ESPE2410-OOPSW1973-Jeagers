package ec.edu.espe.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.bson.Document;

/**
 *
 * @author Dennis Paucar
 */
public class QuoteChangeLog {
    private String projectId;
    private String projectTitle;
    private double oldQuote;
    private double newQuote;
    private Date changeDate;

    public QuoteChangeLog(String projectId, String projectTitle, double oldQuote, double newQuote, Date changeDate) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.oldQuote = oldQuote;
        this.newQuote = newQuote;
        this.changeDate = changeDate;
    }
    
    public void displayChangeLog() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("+--------------------------------------------------------------+");
        System.out.printf("| %-60s |\n", "Registro de Cambios en Cotizacion");
        System.out.println("+--------------------------------------------------------------+");

        System.out.printf("| %-18s | %-45s |\n", "ID Proyecto:", projectId);
        System.out.printf("| %-18s | %-45s |\n", "Titulo Proyecto:", projectTitle);
        System.out.println("+--------------------------------------------------------------+");

        System.out.printf("| %-18s | %-45.2f |\n", "Cotizacion Anterior:", oldQuote);
        System.out.printf("| %-18s | %-45.2f |\n", "Nueva Cotizacion:", newQuote);
        System.out.println("+--------------------------------------------------------------+");

        System.out.printf("| %-18s | %-45s |\n", "Fecha del Cambio:", changeDate != null ? dateFormat.format(changeDate) : "No registrada");
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

    public double getOldQuote() {
        return oldQuote;
    }

    public void setOldQuote(double oldQuote) {
        this.oldQuote = oldQuote;
    }

    public double getNewQuote() {
        return newQuote;
    }

    public void setNewQuote(double newQuote) {
        this.newQuote = newQuote;
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
            .append("oldQuote", oldQuote)
            .append("newQuote", newQuote)
            .append("changeDate", changeDate != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(changeDate) : null)
            .toJson();
}

    
    
}
