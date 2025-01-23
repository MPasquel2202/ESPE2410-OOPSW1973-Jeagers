package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import ec.edu.espe.model.QuoteChangeLog;
import org.bson.Document;
import utils.MongoDBConnection;

/**
 *
 * @author Dennis Paucar
 */
public class QuoteChangeLogController extends BaseController<QuoteChangeLog>{

    @Override
    protected MongoCollection<Document> getCollection() {
        return MongoDBConnection.getDatabase().getCollection("QuoteChangeLogs");
    }
    
    public void saveQuoteChangeLog(QuoteChangeLog quoteChangeLog){
        save(quoteChangeLog);
    }
    
}
