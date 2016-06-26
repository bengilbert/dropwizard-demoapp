package mergermarkets.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StockPriceServiceTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(0);

    @Test
    public void shouldBeAbleToRetriveStockPriceForKnownTickerCode() throws URISyntaxException {
        final String googResponse = "{\"tickerCode\":\"GOOG\"," +
                "\"latestPrice\":54407," +
                "\"priceUnits\":\"GBP:pence\"," +
                "\"asOf\":\"2016-06-26T09:33:11.481Z\"," +
                "\"storyFeedUrl\":\"http://mm-recruitment-story-feed-api.herokuapp.com/8271\"}";
        wireMockRule.stubFor(get(urlEqualTo("/company/GOOG")).willReturn(aResponse().withBody(googResponse).withHeader("Content-Type", "application/json")));

        StockPriceService stockPriceService = new StockPriceService(String.format("http://localhost:%s/company", wireMockRule.port()));
        Optional<StockPrice> stockPrice = stockPriceService.getStockPriceForTickerCode(new TickerCode("GOOG"));

        assertThat(stockPrice.isPresent(), is(true));
        assertThat(stockPrice.get().getLatestPrice(), is(54407L));
        assertThat(stockPrice.get().getPriceUnits(), is("GBP:pence"));
        assertThat(stockPrice.get().getStoryFeedUrl(), is(new URI("http://mm-recruitment-story-feed-api.herokuapp.com/8271")));
    }

    @Test
    public void emptyPriceReturnedWhenTickerCodeDoesntExist() throws URISyntaxException {
        wireMockRule.stubFor(get(urlEqualTo("/company/GOOG")).willReturn(aResponse().withStatus(404)));

        StockPriceService stockPriceService = new StockPriceService(String.format("http://localhost:%s/company", wireMockRule.port()));
        Optional<StockPrice> stockPrice = stockPriceService.getStockPriceForTickerCode(new TickerCode("GOOG"));

        assertThat(stockPrice.isPresent(), is(false));
    }
}