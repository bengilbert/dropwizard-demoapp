package mergermarkets.resource;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.ImmutableList;
import mergermarkets.service.TickerCodeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;


@Path("/companies")
@Produces(MediaType.APPLICATION_JSON)
public class CompaniesResource {

    private TickerCodeService tickerCodeService;

    public CompaniesResource(TickerCodeService tickerCodeService) {

        this.tickerCodeService = tickerCodeService;
    }

    @GET
    @Timed
    public List<Company> getAllCompanyInformation() {
        return ImmutableList.of(new Company("GOOG"));
    }

    @GET
    @Path("/{tickerCode}")
    public Company getCompany(@PathParam("tickerCode") final String tickerCode) {
        Optional<String> companyName = tickerCodeService.getCompanyName(tickerCode);

        if (companyName.isPresent()) {
            return new Company(companyName.get());
        }

        throw new NotFoundException();
    }

}



