
package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import ec.edu.espe.model.Project;
import org.bson.Document;
import utils.MongoDBConnection;

/**
 *
 * @author Dennis Paucar
 */
public class ProjectController extends BaseController<Project> {

    @Override
    protected MongoCollection<Document> getCollection() {
        return MongoDBConnection.getDatabase().getCollection("Projects");
    }
    
    public void saveProject(Project project){
        save(project);      
    }
}
