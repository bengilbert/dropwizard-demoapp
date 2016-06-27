package mergermarkets.resource;

import com.google.common.collect.ImmutableList;
import io.dropwizard.testing.junit.ResourceTestRule;
import mergermarkets.service.news.NewsService;
import mergermarkets.service.news.NewsStory;
import mergermarkets.service.sentiment.Sentiment;
import mergermarkets.service.stockprice.StockPrice;
import mergermarkets.service.stockprice.StockPriceService;
import mergermarkets.service.tickercode.TickerCode;
import mergermarkets.service.tickercode.TickerCodeService;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;


public class CompaniesResourceTest {

    private static final TickerCodeService mockTickerCodeService = mock(TickerCodeService.class);
    private static final StockPriceService mockStockPriceService = mock(StockPriceService.class);
    private static final NewsService mockNewsService = mock(NewsService.class);


    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new CompaniesResource(mockTickerCodeService, mockStockPriceService, mockNewsService))
            .build();


    @Before
    public void setup() throws URISyntaxException {
        when(mockTickerCodeService.getCompanyName(any())).thenReturn(Optional.of("Google Inc"));
        when(mockStockPriceService.getStockPriceForTickerCode(any())).thenReturn(Optional.of(new StockPrice(new TickerCode("GOOG"), 1000L, "GBP:pence", new URI("http:/localhost/1234"))));
    }

    @After
    public void tearDown(){
        reset(mockTickerCodeService);
        reset(mockStockPriceService);
        reset(mockNewsService);
    }

    @Test
    public void newsStoriesShouldHaveSentiment() {
        when(mockNewsService.getNewsStories(any())).thenReturn(ImmutableList.of(new NewsStory("headline", "body", Sentiment.POSITIVE)));
        final Company company = resources.client().target("/companies/GOOG").request().get(Company.class);
        assertThat(company.newsStories().isEmpty(), is(false));
        assertThat(company.newsStories().get(0).getSentiment(), is("POSITIVE"));
    }

}