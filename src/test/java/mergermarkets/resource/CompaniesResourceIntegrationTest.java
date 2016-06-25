package mergermarkets.resource;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.dropwizard.testing.junit.DropwizardAppRule;
import mergermarkets.dropwizard.App;
import mergermarkets.dropwizard.AppConfig;
import org.junit.ClassRule;
import org.junit.Test;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertThat;


public class CompaniesResourceIntegrationTest {

    @ClassRule
    public static DropwizardAppRule dropwizardAppRule = new DropwizardAppRule<AppConfig>(App.class, resourceFilePath("config.yml"));

    @Test
    public void allCompaniesCanBeRequested() throws UnirestException {

        HttpResponse<JsonNode> response = Unirest.get("http://localhost:8080/companies").asJson();

        assertThat(response.getStatus(), is(200));
        assertThat(response.getBody().getArray().length(), greaterThan(0));
    }
}