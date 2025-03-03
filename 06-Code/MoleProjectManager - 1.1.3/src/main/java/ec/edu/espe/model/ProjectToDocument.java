package ec.edu.espe.model;

import java.text.SimpleDateFormat;
import org.bson.Document;

/**
 *
 * @author Brandon Pazmino
 */
public class ProjectToDocument {
    private Project project;

    public ProjectToDocument(Project project) {
        this.project = project;
    }
    public String toJson() {
        return new Document("projectId", project.getProjectId())
                .append("projectTitle", project.getProjectTitle())
                .append("projectDescription", project.getProjectDescription())
                .append("customer", project.getCustomer() != null ? Document.parse(project.getCustomer().toString()) : null)
                .append("startDate", project.getStartDate() != null ? new SimpleDateFormat("yyyy-MM-dd").format(project.getStartDate()) : null)
                .append("closingDate", project.getClosingDate() != null ? new SimpleDateFormat("yyyy-MM-dd").format(project.getClosingDate()) : null)
                .append("startquote", project.getStartquote())
                .append("operationalStatus", project.getOperationalStatus() != null ? project.getOperationalStatus().toString() : null)
                .append("quoteStatus", project.getQuoteStatus() != null ? project.getQuoteStatus().toString() : null)
                .append("paid", project.isPaid())
                .append("invoiced", project.isInvoiced())
                .append("isPublic", project.isPublic())
                .toJson();
    }
    
    
}
