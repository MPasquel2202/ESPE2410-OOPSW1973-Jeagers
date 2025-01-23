package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import ec.edu.espe.model.StatusChangeLog;
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
    
    public void saveStatusChangeLog(StatusChangeLog statusChangeLog){
        save(statusChangeLog);
    }
    
}
