package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import ec.edu.espe.model.Customer;
import java.util.ArrayList;
import java.util.List;
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
    public List<Customer> findAllCustomers(){
        List<Customer> customers = new ArrayList<>();
        MongoCollection<Document> collection = getCollection();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Customer customer = new Customer(
                    doc.getString("id"),
                    doc.getString("RUC"),
                    doc.getString("name"),
                    doc.getString("Phone"),
                    doc.getString("Email"),
                    doc.getString("Dirección")
                );
                customers.add(customer);
            }
        }

        return customers;
    }
    
}



