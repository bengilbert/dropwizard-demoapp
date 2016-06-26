package mergermarkets.resource;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.ImmutableList;
import mergermarkets.service.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;


@Path("/companies")
@Produces(MediaType.APPLICATION_JSON)
public class CompaniesResource {

    private TickerCodeService tickerCodeService;
    private StockPriceService stockPriceService;
    private NewsService newsService;

    public CompaniesResource(final TickerCodeService tickerCodeService, final StockPriceService stockPriceService, final NewsService newsService) {
        this.tickerCodeService = tickerCodeService;
        this.stockPriceService = stockPriceService;
        this.newsService = newsService;
    }

    @GET
    @Timed
    public List<Company> getAllCompanyInformation() {
        return ImmutableList.of(new Company("GOOG"));
    }

    @GET
    @Path("/{tickerCode}")
    public Company getCompany(@PathParam("tickerCode") final String tickerCode) {
        TickerCode tickerCode1 = new TickerCode(tickerCode);
        Optional<String> companyName = tickerCodeService.getCompanyName(tickerCode1);

        if (companyName.isPresent()) {
            Company company = new Company(companyName.get());
            Optional<StockPrice> stockPrice = stockPriceService.getStockPriceForTickerCode(tickerCode1);

            if (stockPrice.isPresent()) {
                company.setStockPrice(stockPrice.get());

                List<NewsStory> newsStories = newsService.getNewsStories(stockPrice.get().getStoryFeedUrl());
                company.setNewsStories(newsStories);
            }

            return company;
        }

        throw new NotFoundException();
    }

}



