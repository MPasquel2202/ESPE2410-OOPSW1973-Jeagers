package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ec.edu.espe.model.Support;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import utils.MongoDBConnection;

/**
 *
 * @author Dennis Paucar
 */
public class SupportController extends BaseController<Support> {

    @Override
    protected MongoCollection<Document> getCollection() {
        return MongoDBConnection.getDatabase().getCollection("Supports");
    }
    
    public void saveSupport(Support support){
        save(support);
    }
    
    public List<Support> findAllSupports() {
        List<Support> supports = new ArrayList<>();
        MongoCollection<Document> collection = getCollection();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                String supportId = doc.getString("supportId");
                String projectId = doc.getString("projectId");
                String projectTitle= doc.getString("projectTitle");
                String supportDetails = doc.getString("supportDetails");
                String supportStatus = doc.getString("supportStatus");
                int durationYears = doc.getInteger("durationYears");
                String scheduleType = doc.getString("scheduleType");

                String startDateStr = doc.getString("startDate");
                String endDateStr = doc.getString("endDate");

                Date startDate = null;
                Date endDate = null;

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                if (startDateStr != null) {
                    try {
                        startDate = dateFormat.parse(startDateStr);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (endDateStr != null) {
                    try {
                        endDate = dateFormat.parse(endDateStr);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Support support = new Support(supportId, projectId, projectTitle, supportDetails, startDate, endDate, supportStatus, durationYears, scheduleType);
                supports.add(support);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return supports;
    }
    
    public String generateSupportId(int durationYears, String scheduleType) {
        String durationLabel = durationYears + "A";
        String scheduleLabel = scheduleType.equals("8x5") ? "8*5" : "24*7";

        if (durationYears == 1 && scheduleType.equals("8x5")) {
            return "SRV_01 (" + durationLabel + " " + scheduleLabel + ")";
        } else if (durationYears == 1 && scheduleType.equals("24x7")) {
            return "SRV_02 (" + durationLabel + " " + scheduleLabel + ")";
        } else if (durationYears == 3 && scheduleType.equals("8x5")) {
            return "SRV_03 (" + durationLabel + " " + scheduleLabel + ")";
        } else if (durationYears == 3 && scheduleType.equals("24x7")) {
            return "SRV_04 (" + durationLabel + " " + scheduleLabel + ")";
        } else if (durationYears == 5 && scheduleType.equals("8x5")) {
            return "SRV_05 (" + durationLabel + " " + scheduleLabel + ")";
        } else if (durationYears == 5 && scheduleType.equals("24x7")) {
            return "SRV_06 (" + durationLabel + " " + scheduleLabel + ")";
        } else {
            return "SRV_UNKNOWN (" + durationLabel + " " + scheduleLabel + ")";
        }
    }
    
    public Date calculateEndDateOfSupport(Date startDate, int durationYears) {
        int monthsOfSupport = durationYears * 12;
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, monthsOfSupport);
        return calendar.getTime();
    }
}
