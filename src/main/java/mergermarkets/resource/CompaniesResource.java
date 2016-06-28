package mergermarkets.resource;

import com.codahale.metrics.annotation.Timed;
import mergermarkets.service.news.NewsService;
import mergermarkets.service.news.NewsStory;
import mergermarkets.service.stockprice.StockPrice;
import mergermarkets.service.stockprice.StockPriceService;
import mergermarkets.service.tickercode.TickerCode;
import mergermarkets.service.tickercode.TickerCodeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Path("/api/companies")
@Produces(MediaType.APPLICATION_JSON)
public class CompaniesResource {

    private TickerCodeService tickerCodeService;
    private StockPriceService stockPriceService;
    private NewsService newsService;
    private ModelMapper modelMapper;

    private static Type targetListType = new TypeToken<List<CompanyNews>>() {
    }.getType();

    public CompaniesResource(final TickerCodeService tickerCodeService, final StockPriceService stockPriceService, final NewsService newsService) {
        this.tickerCodeService = tickerCodeService;
        this.stockPriceService = stockPriceService;
        this.newsService = newsService;
        this.modelMapper = new ModelMapper();
    }

    @GET
    @Timed
    public List<CompanySummary> getTickerCodes() {
        List<String> tickerCodes = tickerCodeService.getAllTickerCodes();
        return tickerCodes.stream().map(tickerCode -> new CompanySummary(tickerCode)).collect(Collectors.toList());
    }

    @GET
    @Timed
    @Path("/{tickerCode}")
    public Company getCompany(@PathParam("tickerCode") final String tickerCode) {
        TickerCode tickerCode1 = new TickerCode(tickerCode);
        Optional<String> companyName = tickerCodeService.getCompanyName(tickerCode1);

        if (companyName.isPresent()) {
            Company company = new Company(companyName.get());
            Optional<StockPrice> stockPrice = stockPriceService.getStockPriceForTickerCode(tickerCode1);

            if (stockPrice.isPresent()) {
                company.setStockPrice(stockPrice.get().getLatestPrice());

                List<NewsStory> newsStories = newsService.getNewsStories(stockPrice.get().getStoryFeedUrl());
                company.setNewsStories(modelMapper.map(newsStories, targetListType));
            }

            return company;
        }

        throw new NotFoundException();
    }

}



