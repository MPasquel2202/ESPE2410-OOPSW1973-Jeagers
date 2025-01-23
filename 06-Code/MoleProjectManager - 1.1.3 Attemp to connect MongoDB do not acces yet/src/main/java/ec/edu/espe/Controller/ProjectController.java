package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import ec.edu.espe.model.Customer;
import ec.edu.espe.model.Project;
import ec.edu.espe.model.ProjectStatus;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import utils.MongoDBConnection;

public class ProjectController extends BaseController<Project> {

    @Override
    protected MongoCollection<Document> getCollection() {
        return MongoDBConnection.getDatabase().getCollection("Projects");
    }

    public void saveProject(Project project) {
        MongoCollection<Document> collection = getCollection();

        Document query = new Document("projectId", project.getProjectId()); 
        Document updatedData = new Document()
                .append("projectId", project.getProjectId())
                .append("projectTitle", project.getProjectTitle())
                .append("projectDescription", project.getProjectDescription())
                .append("customer", new Document()
                        .append("id", project.getCustomer().getCustomerId())
                        .append("RUC", project.getCustomer().getRuc())
                        .append("name", project.getCustomer().getName())
                        .append("Phone", project.getCustomer().getPhoneNumber())
                        .append("Email", project.getCustomer().getEmail())
                        .append("Dirección", project.getCustomer().getAddress()))
                .append("startDate", project.getStartDate() != null ? new SimpleDateFormat("yyyy-MM-dd").format(project.getStartDate()) : null)
                .append("closingDate", project.getClosingDate() != null ? new SimpleDateFormat("yyyy-MM-dd").format(project.getClosingDate()) : null)
                .append("startquote", project.getStartquote())
                .append("operationalStatus", project.getOperationalStatus().toString())
                .append("quoteStatus", project.getQuoteStatus().toString())
                .append("paid", project.isPaid())
                .append("invoiced", project.isInvoiced())
                .append("isPublic", project.isIsPublic());

        collection.replaceOne(query, updatedData, new ReplaceOptions().upsert(true));
    }

    public List<Project> findAllProjects() {
        List<Project> projects = new ArrayList<>();
        MongoCollection<Document> collection = getCollection();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                ProjectStatus operationalStatus = ProjectStatus.fromString(doc.getString("operationalStatus"));
                ProjectStatus quoteStatus = ProjectStatus.fromString(doc.getString("quoteStatus"));

                Document customerDoc = (Document) doc.get("customer");  
                Customer customer = new Customer(
                    customerDoc.getString("id"),
                    customerDoc.getString("RUC"),
                    customerDoc.getString("name"),
                    customerDoc.getString("Phone"),
                    customerDoc.getString("Email"),
                    customerDoc.getString("Dirección")
                );

                String startDateStr = doc.getString("startDate");
                Date startDate = null;
                if (startDateStr != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
                    startDate = sdf.parse(startDateStr);  
                }

                String closingDateStr = doc.getString("closingDate");
                Date closingDate = null;
                if (closingDateStr != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    closingDate = sdf.parse(closingDateStr);  
                }

                Project project = new Project(
                    doc.getString("projectId"),
                    doc.getString("projectTitle"),
                    doc.getString("projectDescription"),
                    customer,  
                    startDate,  
                    closingDate,  
                    doc.getDouble("startquote"),
                    operationalStatus,
                    quoteStatus,
                    doc.getBoolean("paid"),
                    doc.getBoolean("invoiced"),
                    doc.getBoolean("isPublic")
                );

                projects.add(project);
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return projects;
    }
    
    
    public List<Project> getClosedProjects() {
    List<Project> closedProjects = new ArrayList<>();
    for (Document doc : getCollection().find(Filters.eq("operationalStatus", ProjectStatus.CLOSED.getStatus()))) {
        String id = doc.getString("projectId");
        String description = doc.getString("projectDescription"); 
        String status = doc.getString("operationalStatus");

        ProjectStatus projectStatus = ProjectStatus.fromString(status);

        Project project = new Project(id, description, projectStatus);
        
        closedProjects.add(project);
    }
    return closedProjects;
    }

}
