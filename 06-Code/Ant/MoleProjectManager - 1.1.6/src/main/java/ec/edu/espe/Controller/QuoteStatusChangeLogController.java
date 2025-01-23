package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import ec.edu.espe.model.QuoteStatusChangeLog;
import org.bson.Document;
import utils.MongoDBConnection;

/**
 *
 * @author Dennis Paucar
 */
public class QuoteStatusChangeLogController extends BaseController<QuoteStatusChangeLog>{

    @Override
    protected MongoCollection<Document> getCollection() {
        return MongoDBConnection.getDatabase().getCollection("QuoteStatusChangeLogs");
    }
    
    public void saveQuoteStatusChangeLog(QuoteStatusChangeLog quoteStatusChangeLog){
        save(quoteStatusChangeLog);
    }
    
}
