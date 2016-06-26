package mergermarkets.service;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Optional;

public class TickerCodeService {

    private final MongoDatabase db;

    public TickerCodeService(final String connectionString) {
        MongoClientURI mongoClientURI = new MongoClientURI(connectionString);
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        db = mongoClient.getDatabase(mongoClientURI.getDatabase());
    }

    public Optional<String> getCompanyName(final TickerCode tickerCode) {
        MongoCollection<Document> companyCollection = db.getCollection("company");
        BasicDBObject query = new BasicDBObject("tickerCode", tickerCode.getCode());
        Document document = companyCollection.find(query).first();

        if (document != null) {
            return Optional.of(document.getString("name"));
        }

        return Optional.empty();
    }
}
