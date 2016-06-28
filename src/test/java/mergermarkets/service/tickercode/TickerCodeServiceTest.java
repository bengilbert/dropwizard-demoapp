package mergermarkets.service.tickercode;

import com.google.common.collect.ImmutableMap;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TickerCodeServiceTest {

    @ClassRule
    public static GenericContainer mongodb =
            new GenericContainer("mongo:3.3").withExposedPorts(27017);

    private MongoDatabase db;
    private TickerCodeService tickerCodeService;

    @Before
    public void setup() {
        MongoClient mongoClient = new MongoClient(mongodb.getIpAddress(), mongodb.getMappedPort(27017));
        db = mongoClient.getDatabase("test");

        if (db.getCollection("company") == null) {
            db.createCollection("company");
        }

        String mongoUrl = "mongodb://" + mongodb.getIpAddress() + ":" + mongodb.getMappedPort(27017) + "/test";
        tickerCodeService = new TickerCodeService(mongoUrl);
    }

    @After
    public void cleanup() {
        db.drop();
    }

    @Test
    public void canGetCompanyDetailsForTickerCode() {
        db.getCollection("company").insertOne(new Document(ImmutableMap.of("name", "Google Inc", "tickerCode", "GOOG")));
        Optional<String> companyName = tickerCodeService.getCompanyName(new TickerCode("GOOG"));

        assertThat(companyName.get(), is("Google Inc"));
    }

    @Test
    public void emptyCompanyNameReturnedForUnknownTickerCode() {
        Optional<String> companyName = tickerCodeService.getCompanyName(new TickerCode("unknown"));

        assertThat(companyName.isPresent(), is(false));
    }

    @Test
    public void allTickerCodesCanBeRequested() {
        db.getCollection("company").insertOne(new Document(ImmutableMap.of("name", "Google Inc", "tickerCode", "GOOG")));
        db.getCollection("company").insertOne(new Document(ImmutableMap.of("name", "Microsoft", "tickerCode", "MSFT")));
        db.getCollection("company").insertOne(new Document(ImmutableMap.of("name", "Sky", "tickerCode", "SKY")));

        List<String> tickerCodes = tickerCodeService.getAllTickerCodes();

        assertThat(tickerCodes.size(), is(3));
        assertThat(tickerCodes, containsInAnyOrder("GOOG", "MSFT", "SKY"));

    }

}