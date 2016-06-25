package mergermarkets.resource;

import com.google.common.collect.ImmutableMap;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.junit.DropwizardAppRule;
import mergermarkets.dropwizard.App;
import mergermarkets.dropwizard.AppConfig;
import org.bson.Document;
import org.junit.*;
import org.testcontainers.containers.GenericContainer;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertThat;


public class CompaniesResourceIntegrationTest {

    @ClassRule
    public static GenericContainer mongodb =
            new GenericContainer("mongo:3.3").withExposedPorts(27017);

    @Rule
    public DropwizardAppRule dropwizardAppRule = new DropwizardAppRule<AppConfig>(App.class, resourceFilePath("config.yml"),
            ConfigOverride.config("tickerCodeMongoDb", String.format("mongodb://%s:%s/test", mongodb.getContainerIpAddress(), mongodb.getMappedPort(27017))));

    private MongoDatabase db;

    @Before
    public void setup() {
        MongoClient mongoClient = new MongoClient(mongodb.getIpAddress(), mongodb.getMappedPort(27017));
        db = mongoClient.getDatabase("test");

        if (db.getCollection("company") == null) {
            db.createCollection("company");
        }

        db.getCollection("company").insertOne(new Document(ImmutableMap.of("name", "Google Inc", "tickerCode", "GOOG")));
    }

    @After
    public void tearDown() {
        db.drop();
    }

    @Test
    public void allCompaniesCanBeRequested() throws UnirestException {

        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/companies").asJson();

        assertThat(response.getStatus(), is(200));
        assertThat(response.getBody().getArray().length(), greaterThan(0));
    }

    @Test
    public void companyNameMatchesTickerCode() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/companies/GOOG").asJson();

        assertThat(response.getStatus(), is(200));
        assertThat(response.getBody().getObject().get("companyName"), is("Google Inc"));

    }

    @Test
    public void requestingAnUnknownTickerCodeReturns404() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/companies/unknown").asJson();

        assertThat(response.getStatus(), is(404));
    }
}