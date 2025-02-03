package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import ec.edu.espe.model.Project;
import ec.edu.espe.model.ProjectStatus;
import ec.edu.espe.model.StatusChangeLog;
import java.util.Date;
import org.bson.Document;
import utils.MongoDBConnection;

/**
 *
 * @author Dennis Paucar
 */
public class StatusChangeLogController extends BaseController<StatusChangeLog>{

    @Override
    protected MongoCollection<Document> getCollection() {
        return MongoDBConnection.getDatabase().getCollection("StatusChangeLogs");
    }
    
    
    
    public void saveStatusChangeLog(StatusChangeLog statusChangeLog) {
        MongoCollection<Document> collection = getCollection();

        Document logDocument = new Document("projectId", statusChangeLog.getProjectId())
                .append("projectTitle", statusChangeLog.getProjectTitle())
                .append("oldStatus", statusChangeLog.getOldStatus())
                .append("newStatus", statusChangeLog.getNewStatus())
                .append("changeDate", statusChangeLog.getChangeDate());

        collection.insertOne(logDocument); 
    }

 
    
}
