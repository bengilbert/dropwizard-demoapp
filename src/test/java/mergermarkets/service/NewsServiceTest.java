package mergermarkets.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Rule;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NewsServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(0);

    @Test
    public void canRetrieveNewsStories() throws URISyntaxException, UnirestException {
        NewsService newsService = new NewsService();

        URI uri = new URI(String.format("http://localhost:%s/8271", wireMockRule.port()));
        final String newsResponse = "[{\"id\":74," +
                "\"headline\":\"Google going strong, but maybe not for long.\"," +
                "\"body\":\"Google has some concerns to address the balance of this year, and beyond.\"" +
                "},{" +
                "\"id\":141," +
                "\"headline\":\"Ad revenues still primary source of Google revenue.\"," +
                "\"body\":\"Investors were encouraged by a healthy gain in the number of people looking" +
                "\"}]";
        wireMockRule.stubFor(get(urlEqualTo("/8271")).willReturn(aResponse().withBody(newsResponse).withHeader("Content-Type", "application/json")));

        List<NewsStory> newsStories = newsService.getNewsStories(uri);

        assertThat(newsStories.size(), is(2));
        assertThat(newsStories.get(0).getBody(), is("Google has some concerns to address the balance of this year, and beyond."));
        assertThat(newsStories.get(0).getHeadline(), is("Google going strong, but maybe not for long."));
    }

    @Test
    public void emptyListReturnedWhenThereAreNoStoriesAvailable() throws URISyntaxException, UnirestException {
        NewsService newsService = new NewsService();

        URI uri = new URI(String.format("http://localhost:%s/8271", wireMockRule.port()));
        wireMockRule.stubFor(get(urlEqualTo("/8271")).willReturn(aResponse().withBody("[]").withHeader("Content-Type", "application/json")));

        List<NewsStory> newsStories = newsService.getNewsStories(uri);

        assertThat(newsStories.isEmpty(), is(true));
    }

    @Test
    public void emptyListReturnedWhenRequest404s() throws URISyntaxException, UnirestException {
        NewsService newsService = new NewsService();

        URI uri = new URI(String.format("http://localhost:%s/8271", wireMockRule.port()));
        wireMockRule.stubFor(get(urlEqualTo("/8271")).willReturn(aResponse().withStatus(404)));

        List<NewsStory> newsStories = newsService.getNewsStories(uri);

        assertThat(newsStories.isEmpty(), is(true));
    }

}