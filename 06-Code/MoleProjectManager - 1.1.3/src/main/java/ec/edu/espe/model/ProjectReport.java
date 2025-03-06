package ec.edu.espe.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 * 
 * @author David Pilatasig
 */

public class ProjectReport {

    private String reportId;
    private Project project;
    private List<QuoteChangeLog> quoteChangeLogs;
    private List<StatusChangeLog> statusChangeLogs;
    private List<QuoteStatusChangeLog> quoteStatusChangeLogs;
    private List<Support> supports;

    public ProjectReport(String reportId, Project project, List<QuoteChangeLog> quoteChangeLogs,
                  List<StatusChangeLog> statusChangeLogs, List<QuoteStatusChangeLog> quoteStatusChangeLogs,
                  List<Support> supports) {
        this.reportId = reportId;
        this.project = project;
        this.quoteChangeLogs = quoteChangeLogs;
        this.statusChangeLogs = statusChangeLogs;
        this.quoteStatusChangeLogs = quoteStatusChangeLogs;
        this.supports = supports;
    }

    


    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<QuoteChangeLog> getQuoteChangeLogs() {
        return quoteChangeLogs;
    }

    public void setQuoteChangeLogs(List<QuoteChangeLog> quoteChangeLogs) {
        this.quoteChangeLogs = quoteChangeLogs;
    }

    public List<StatusChangeLog> getStatusChangeLogs() {
        return statusChangeLogs;
    }

    public void setStatusChangeLogs(List<StatusChangeLog> statusChangeLogs) {
        this.statusChangeLogs = statusChangeLogs;
    }

    public List<QuoteStatusChangeLog> getQuoteStatusChangeLogs() {
        return quoteStatusChangeLogs;
    }

    public void setQuoteStatusChangeLogs(List<QuoteStatusChangeLog> quoteStatusChangeLogs) {
        this.quoteStatusChangeLogs = quoteStatusChangeLogs;
    }

    public List<Support> getSupports() {
        return supports;
    }

    public void setSupports(List<Support> supports) {
        this.supports = supports;
    }
    
    @Override
public String toString() {
    return new Document("reportId", reportId)
            .append("project", new Document("projectId", project.getProjectId())
                    .append("projectTitle", project.getProjectTitle())
                    .append("projectDescription", project.getProjectDescription())
                    .append("startDate", project.getStartDate() != null ? new SimpleDateFormat("yyyy-MM-dd").format(project.getStartDate()) : null)
                    .append("closingDate", project.getClosingDate() != null ? new SimpleDateFormat("yyyy-MM-dd").format(project.getClosingDate()) : null)
                    .append("customer", new Document("customerId", project.getCustomer().getCustomerId())
                            .append("customerName", project.getCustomer().getName()))
                    .append("startquote", project.getStartquote())
                    .append("operationalStatus", project.getOperationalStatus().getStatus())
                    .append("quoteStatus", project.getQuoteStatus().getStatus())
                    .append("invoiced", project.isInvoiced())
                    .append("paid", project.isPaid())
                    .append("isPublic", project.isPublic()))
            .append("quoteChangeLogs", quoteChangeLogs != null ? quoteChangeLogs.stream()
                    .map(log -> new Document("projectTitle", log.getProjectTitle())
                            .append("oldQuote", log.getOldQuote())
                            .append("newQuote", log.getNewQuote())
                            .append("changeDate", log.getChangeDate() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(log.getChangeDate()) : null))
                    .toList() : new ArrayList<>())
            .append("statusChangeLogs", statusChangeLogs != null ? statusChangeLogs.stream()
                    .map(log -> new Document("projectTitle", log.getProjectTitle())
                            .append("oldStatus", log.getOldStatus())
                            .append("newStatus", log.getNewStatus())
                            .append("changeDate", log.getChangeDate() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(log.getChangeDate()) : null))
                    .toList() : new ArrayList<>())
            .append("quoteStatusChangeLogs", quoteStatusChangeLogs != null ? quoteStatusChangeLogs.stream()
                    .map(log -> new Document("projectTitle", log.getProjectTitle())
                            .append("oldQuoteStatus", log.getOldQuoteStatus())
                            .append("newQuoteStatus", log.getNewQuoteStatus())
                            .append("changeDate", log.getChangeDate() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(log.getChangeDate()) : null))
                    .toList() : new ArrayList<>())
            .append("supports", supports != null ? supports.stream()
                    .map(support -> new Document("supportId", support.getSupportId())
                            .append("projectDescription", support.getProjectTitle())
                            .append("supportDetails", support.getSupportDetails())
                            .append("supportStatus", support.getSupportStatus()))
                    .toList() : new ArrayList<>())
            .toJson();
}

}
