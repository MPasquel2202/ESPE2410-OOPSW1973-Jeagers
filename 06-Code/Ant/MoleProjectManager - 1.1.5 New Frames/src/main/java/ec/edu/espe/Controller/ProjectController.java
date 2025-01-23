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
 * @author Dennis Paucar
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
                Customer customer = new Customer(
                        doc.getString("customerId"),
                        doc.getString("ruc"),
                        doc.getString("name"),
                        doc.getString("phoneNumber"),
                        doc.getString("email"),
                        doc.getString("address"));

                ProjectStatus operationalStatus;
                ProjectStatus quoteStatus;

                try {
                    operationalStatus = ProjectStatus.fromString(doc.getString("operationalStatus"));
                } catch (IllegalArgumentException e) {
                    operationalStatus = ProjectStatus.CREATED;
                }

                try {
                    quoteStatus = ProjectStatus.fromString(doc.getString("quoteStatus"));
                } catch (IllegalArgumentException e) {
                    quoteStatus = ProjectStatus.QUOTE_SEND;
                }
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = null;
                Date closingDate = null;

                try {
                    if (doc.getString("startDate") != null) {
                        startDate = dateFormat.parse(doc.getString("startDate"));
                    }
                    if (doc.getString("closingDate") != null) {
                        closingDate = dateFormat.parse(doc.getString("closingDate"));
                    }
                } catch (Exception e) {
                    System.out.println("Error al convertir fechas: " + e.getMessage());
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
            return projects;
        }
    }
}
