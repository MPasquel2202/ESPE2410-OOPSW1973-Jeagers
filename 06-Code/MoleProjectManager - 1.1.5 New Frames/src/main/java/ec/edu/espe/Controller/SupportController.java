package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import ec.edu.espe.model.Support;
import org.bson.Document;
import utils.MongoDBConnection;

/**
 *
 * @author Dennis Paucar
 */
public class SupportController extends BaseController<Support> {

    @Override
    protected MongoCollection<Document> getCollection() {
        return MongoDBConnection.getDatabase().getCollection("Support");
    }
    
    public void saveSupport(Support support){
        save(support);
    }
    
}
