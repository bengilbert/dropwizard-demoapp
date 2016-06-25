package mergermarkets;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class TickerCodeService {

    public String getCompanyName(final String tickerCode) {

        MongoClientURI connectionString = new MongoClientURI("mongodb://mm_recruitment_user_readonly:rebelMutualWhistle@ds037551.mongolab.com:37551/mm-recruitment");
        MongoClient mongoClient = new MongoClient(connectionString);

        MongoDatabase database = mongoClient.getDatabase("mm-recruitment");
        MongoCollection<Document> companyCollection = database.getCollection("company");

        BasicDBObject query = new BasicDBObject("tickerCode", tickerCode);

        Document document = companyCollection.find(query).first();

        return document.getString("name");
    }
}
