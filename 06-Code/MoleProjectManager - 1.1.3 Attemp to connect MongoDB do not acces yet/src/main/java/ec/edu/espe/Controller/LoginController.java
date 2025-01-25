package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ec.edu.espe.model.Login;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import utils.MongoDBConnection;

/**
 *
 * @author Dennis Paucar
 */
public class LoginController extends BaseController<Login>{

    @Override
    protected MongoCollection<Document> getCollection() {
        return MongoDBConnection.getDatabase().getCollection("Credentials");
    }
    public void saveCustomer(Login login){
        save(login);      
    }
    
    public List<Login> findAllLogins() {
        List<Login> logins = new ArrayList<>();
        MongoCollection<Document> collection = getCollection();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Login login = new Login(
                    doc.getString("username"),
                    doc.getString("password")
                );
                logins.add(login);
            }
        }

        return logins;
    }
    
}
