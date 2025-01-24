package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ec.edu.espe.model.QuoteChangeLog;
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
public class QuoteChangeLogController extends BaseController<QuoteChangeLog>{

    @Override
    protected MongoCollection<Document> getCollection() {
        return MongoDBConnection.getDatabase().getCollection("QuoteChangeLogs");
    }
    
    public void saveQuoteChangeLog(QuoteChangeLog quoteChangeLog){
        save(quoteChangeLog);
    }
        public List<QuoteChangeLog> findAllQuoteChangeLogs() {
        List<QuoteChangeLog> changeLogs = new ArrayList<>();
        MongoCollection<Document> collection = getCollection();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                String projectId = doc.getString("projectId");
                String projectTitle = doc.getString("projectTitle");
                double oldQuote = doc.getDouble("oldQuote");
                double newQuote = doc.getDouble("newQuote");
                Date changeDate = parseDate(doc.getString("changeDate"));

                QuoteChangeLog changeLog = new QuoteChangeLog(
                        projectId,
                        projectTitle,
                        oldQuote,
                        newQuote,
                        changeDate
                );
                changeLogs.add(changeLog);
            }
        } catch (Exception e) {
            System.err.println("Error al recuperar los registros de cambios de cotización: " + e.getMessage());
        }

        return changeLogs;
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
