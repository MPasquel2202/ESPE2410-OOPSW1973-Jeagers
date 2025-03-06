package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import ec.edu.espe.model.Customer;
import ec.edu.espe.model.Project;
import ec.edu.espe.model.ProjectStatus;
import ec.edu.espe.model.QuoteChangeLog;
import ec.edu.espe.model.ProjectReport;
import ec.edu.espe.model.StatusChangeLog;
import ec.edu.espe.model.Support;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import utils.MongoDBConnection;

/**
 *
 * @author Dennis Paucar
 */
public class ReportController extends BaseController<ProjectReport>{

    @Override
    protected MongoCollection<Document> getCollection() {
        return MongoDBConnection.getDatabase().getCollection("Reports");
    }
    

public ProjectReport generateReport(String projectId) {
    MongoCollection<Document> projectsCollection = MongoDBConnection.getDatabase().getCollection("Projects");
    MongoCollection<Document> quoteLogsCollection = MongoDBConnection.getDatabase().getCollection("QuoteChangeLogs");
    MongoCollection<Document> statusLogsCollection = MongoDBConnection.getDatabase().getCollection("StatusChangeLogs");
    MongoCollection<Document> supportsCollection = MongoDBConnection.getDatabase().getCollection("Supports");
    
    Document projectDoc = projectsCollection.find(Filters.eq("projectId", projectId)).first();
    if (projectDoc == null) {
        System.out.println("No se encontró el proyecto con ID: " + projectId);
        return null;
    }

    Document customerDoc = projectDoc.get("customer", Document.class);
    Customer customer = new Customer(
            customerDoc.getString("id"),
            customerDoc.getString("RUC"),
            customerDoc.getString("name"),
            customerDoc.getString("Phone"),
            customerDoc.getString("Email"),
            customerDoc.getString("Dirección")
    );

    ProjectStatus operationalStatus = ProjectStatus.fromString(projectDoc.getString("operationalStatus"));
    ProjectStatus quoteStatus = ProjectStatus.fromString(projectDoc.getString("quoteStatus"));

    Project project = new Project.Builder(projectDoc.getString("projectId"), projectDoc.getString("projectTitle"))
            .setProjectDescription(projectDoc.getString("projectDescription"))
            .setCustomer(customer)
            .setStartDate(parseDate(projectDoc.getString("startDate")))
            .setClosingDate(parseDate(projectDoc.getString("closingDate")))
            .setStartquote(projectDoc.getDouble("startquote"))
            .setOperationalStatus(operationalStatus)  
            .setQuoteStatus(quoteStatus)  
            .setPaid(projectDoc.getBoolean("paid"))
            .setInvoiced(projectDoc.getBoolean("invoiced"))
            .setPublic(projectDoc.getBoolean("isPublic"))
            .build();

    List<QuoteChangeLog> quoteLogs = new ArrayList<>();
    try (MongoCursor<Document> quoteCursor = quoteLogsCollection.find(Filters.eq("projectId", projectId)).iterator()) {
        while (quoteCursor.hasNext()) {
            Document doc = quoteCursor.next();
            quoteLogs.add(new QuoteChangeLog(
                    doc.getString("projectId"),
                    doc.getString("projectTitle"),
                    doc.getDouble("oldQuote"),
                    doc.getDouble("newQuote"),
                    doc.getDate("changeDate")
            ));
        }
    }

    List<StatusChangeLog> statusLogs = new ArrayList<>();
    try (MongoCursor<Document> statusCursor = statusLogsCollection.find(Filters.eq("projectId", projectId)).iterator()) {
        while (statusCursor.hasNext()) {
            Document doc = statusCursor.next();
            statusLogs.add(new StatusChangeLog(
                    doc.getString("projectId"),
                    doc.getString("projectTitle"),
                    doc.getString("oldStatus"),
                    doc.getString("newStatus"),
                    doc.getDate("changeDate")
            ));
        }
    }

    List<Support> supports = new ArrayList<>();
    try (MongoCursor<Document> supportCursor = supportsCollection.find(Filters.eq("projectId", projectId)).iterator()) {
        while (supportCursor.hasNext()) {
            Document doc = supportCursor.next();
            supports.add(new Support(
                    doc.getString("supportId"),
                    doc.getString("projectId"),
                    doc.getString("projectTitle"),
                    doc.getString("supportDetails"),
                    doc.getDate("startDate"),
                    doc.getDate("endDate"),
                    doc.getString("supportStatus"),
                    doc.getInteger("durationYears"),
                    doc.getString("scheduleType")
            ));
        }
    }

    ProjectReport report = new ProjectReport(projectId, project, quoteLogs, statusLogs, new ArrayList<>(), supports);

    save(report);

    return report;
}

// Método para parsear fecha de manera segura utilizando SimpleDateFormat
private Date parseDate(String dateString) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try {
        return dateString != null ? dateFormat.parse(dateString) : null;
    } catch (Exception e) {
        System.out.println("Error al convertir fecha: " + e.getMessage());
        return null;  // Si hay un error, se retorna null
    }
}

}
