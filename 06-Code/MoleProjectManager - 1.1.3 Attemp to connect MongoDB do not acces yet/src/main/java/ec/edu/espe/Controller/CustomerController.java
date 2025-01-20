package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import ec.edu.espe.model.Customer;
import org.bson.Document;
import utils.MongoDBConnection;

/**
 * @Autor David Pilatasig
 */

public class CustomerController extends BaseController<Customer> {
    @Override
    public MongoCollection<Document> getCollection() {
        return MongoDBConnection.getDatabase().getCollection("Customers");
    }
    
    public void saveCustomer(Customer customer){
        save(customer);      
    }
}

