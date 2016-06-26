package mergermarkets.resource;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.ImmutableList;
import mergermarkets.service.StockPrice;
import mergermarkets.service.StockPriceService;
import mergermarkets.service.TickerCode;
import mergermarkets.service.TickerCodeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;


@Path("/companies")
@Produces(MediaType.APPLICATION_JSON)
public class CompaniesResource {

    private TickerCodeService tickerCodeService;
    private StockPriceService stockPriceService;

    public CompaniesResource(final TickerCodeService tickerCodeService, final StockPriceService stockPriceService) {
        this.tickerCodeService = tickerCodeService;
        this.stockPriceService = stockPriceService;
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
            }

            return company;
        }

        throw new NotFoundException();
    }

}



