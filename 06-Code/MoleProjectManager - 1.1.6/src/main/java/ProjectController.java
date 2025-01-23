package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import ec.edu.espe.model.Customer;
import ec.edu.espe.model.Project;
import ec.edu.espe.model.ProjectStatus;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import utils.MongoDBConnection;

public class ProjectController extends BaseController<Project> {

    @Override
    protected MongoCollection<Document> getCollection() {
        return MongoDBConnection.getDatabase().getCollection("Projects");
    }

    public void saveProject(Project project){
        save(project);      
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

                Project project = new Project(
                    doc.getString("id"),
                    doc.getString("projectTitle"),
                    doc.getString("projectDescription"),
                    customer,  
                    doc.getDate("startDate"),
                    doc.getDate("closingDate"),
                    doc.getDouble("startquote"),
                    operationalStatus,
                    quoteStatus,
                    doc.getBoolean("paid"),
                    doc.getBoolean("invoiced"),
                    doc.getBoolean("isPublic")
                );

                projects.add(project);
            }
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
