package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ec.edu.espe.model.Project;
import ec.edu.espe.model.Customer;
import ec.edu.espe.model.ProjectStatus;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import utils.MongoDBConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 *
 * @author Brandon Pazmino
 */
public class ProjectController extends BaseController<Project> {

    @Override
    protected MongoCollection<Document> getCollection() {
        return MongoDBConnection.getDatabase().getCollection("Projects");
    }

    public void saveProject(Project project) {
        save(project);
    }

    public List<Project> findAllProjects() {
        List<Project> projects = new ArrayList<>();
        MongoCollection<Document> collection = getCollection();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                Document customerDoc = (Document) doc.get("customer");
                Customer customer = null;

                if (customerDoc != null) {
                    customer = new Customer(
                            customerDoc.getString("id"),
                            customerDoc.getString("RUC"),
                            customerDoc.getString("name"),
                            customerDoc.getString("Phone"),
                            customerDoc.getString("email"),
                            customerDoc.getString("Dirección")
                    );
                }

                ProjectStatus operationalStatus = getProjectStatus(doc.getString("operationalStatus"), ProjectStatus.CREATED);
                ProjectStatus quoteStatus = getProjectStatus(doc.getString("quoteStatus"), ProjectStatus.QUOTE_SEND);

                Date startDate = parseDate(doc.getString("startDate"));
                Date closingDate = parseDate(doc.getString("closingDate"));

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
        }
        return projects;
    }

    private ProjectStatus getProjectStatus(String statusString, ProjectStatus defaultStatus) {
        try {
            return ProjectStatus.fromString(statusString);
        } catch (IllegalArgumentException e) {
            return defaultStatus;
        }
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateString != null ? dateFormat.parse(dateString) : null;
        } catch (Exception e) {
            System.out.println("Error al convertir fecha: " + e.getMessage());
            return null;
        }
    }
}
