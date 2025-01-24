package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ec.edu.espe.model.QuoteStatusChangeLog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
     public List<QuoteStatusChangeLog> findAllQuoteStatusChangeLogs() {
        List<QuoteStatusChangeLog> statusChangeLogs = new ArrayList<>();
        MongoCollection<Document> collection = getCollection();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                String projectId = doc.getString("projectId");
                String projectTitle = doc.getString("projectTitle");
                String oldQuoteStatus = doc.getString("oldQuoteStatus");
                String newQuoteStatus = doc.getString("newQuoteStatus");
                Date changeDate = parseDate(doc.getString("changeDate"));

                QuoteStatusChangeLog statusChangeLog = new QuoteStatusChangeLog(
                        projectId,
                        projectTitle,
                        oldQuoteStatus,
                        newQuoteStatus,
                        changeDate
                );
                statusChangeLogs.add(statusChangeLog);
            }
        } catch (Exception e) {
            System.err.println("Error al recuperar los registros de cambios de estado: " + e.getMessage());
        }

        return statusChangeLogs;
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateString != null ? dateFormat.parse(dateString) : null;
        } catch (Exception e) {
            System.out.println("Error al convertir fecha: " + e.getMessage());
            return null;
        }
    }

    
}
