package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import ec.edu.espe.model.Customer;
import ec.edu.espe.model.Project;
import ec.edu.espe.model.ProjectStatus;
import java.text.ParseException;
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

    public void saveProject(Project project){
        save(project);      
    }

    public List<Project> findAllProjects() {
    List<Project> projects = new ArrayList<>();
    MongoCollection<Document> collection = getCollection();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    try (MongoCursor<Document> cursor = collection.find().iterator()) {
        while (cursor.hasNext()) {
            try {
                Document doc = cursor.next();

                // Convertir los estados de proyecto a enum
                ProjectStatus operationalStatus = ProjectStatus.fromString(doc.getString("operationalStatus"));
                ProjectStatus quoteStatus = ProjectStatus.fromString(doc.getString("quoteStatus"));

                // Obtener la información del cliente
                Document customerDoc = (Document) doc.get("customer");
                Customer customer = customerDoc != null ? new Customer(
                    customerDoc.getString("id"),
                    customerDoc.getString("RUC"),
                    customerDoc.getString("name"),
                    customerDoc.getString("Phone"),
                    customerDoc.getString("Email"),
                    customerDoc.getString("Dirección")
                ) : null;

                // Convertir las fechas de String a Date
                Date startDate = null;
                Date closingDate = null;

                try {
                    // Convertir fecha de inicio
                    String startDateString = doc.getString("startDate");
                    if (startDateString != null && !startDateString.isEmpty()) {
                        startDate = dateFormat.parse(startDateString);
                    }

                    // Convertir fecha de cierre
                    String closingDateString = doc.getString("closingDate");
                    if (closingDateString != null && !closingDateString.isEmpty()) {
                        closingDate = dateFormat.parse(closingDateString);
                    }
                } catch (ParseException e) {
                    System.err.println("Error al convertir las fechas: " + e.getMessage());
                }

                Project project = new Project(
                    doc.getString("id"),
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

            } catch (Exception e) {
                System.err.println("Error al procesar un documento: " + e.getMessage());
                e.printStackTrace();
            }
        }
    } catch (Exception e) {
        System.err.println("Error al consultar la base de datos: " + e.getMessage());
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
