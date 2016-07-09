package mergermarket.service.tickercode;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class TickerCodeService {

    private final MongoDatabase db;

    public TickerCodeService(final String connectionString) {
        MongoClientURI mongoClientURI = new MongoClientURI(connectionString);
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        db = mongoClient.getDatabase(mongoClientURI.getDatabase());
    }

    public Optional<String> getCompanyName(final TickerCode tickerCode) {
        log.debug("Requesting company name for {}", tickerCode);

        MongoCollection<Document> companyCollection = db.getCollection("company");
        BasicDBObject query = new BasicDBObject("tickerCode", tickerCode.getCode());
        Document document = companyCollection.find(query).first();

        if (document != null) {
            return Optional.of(document.getString("name"));
        }

        return Optional.empty();
    }

    public List<String> getAllTickerCodes() {
        log.debug("Requesting all ticker codes");

        List<String> tickerCodes = new ArrayList<>();
        MongoCollection<Document> companyCollection = db.getCollection("company");
        FindIterable<Document> documents = companyCollection.find();
        MongoCursor<Document> iterator = documents.iterator();
        while(iterator.hasNext()) {
            Document document = iterator.next();
            tickerCodes.add(document.getString("tickerCode"));
        }

        return tickerCodes;
    }
}
