package ec.edu.espe.Controller;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;
/**
 *
 * @author Usuario
 */
public abstract class BaseController<T> {
    protected abstract MongoCollection<Document> getCollection();

    public void save(T item) {
        MongoCollection<Document> collection = getCollection();
        Document document = Document.parse(item.toString());
        collection.insertOne(document);
    }

    public List<Document> findAll() {
        MongoCollection<Document> collection = getCollection();
        return collection.find().into(new java.util.ArrayList<>());
    }

    public void delete(String id) {
        MongoCollection<Document> collection = getCollection();
        collection.deleteOne(new Document("_id", id));
    }

    public void update(String id, T updatedItem) {
        MongoCollection<Document> collection = getCollection();
        Document updatedDocument = Document.parse(updatedItem.toString());
        collection.replaceOne(new Document("_id", id), updatedDocument);
    }
}
