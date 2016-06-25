package mergermarkets.resource;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.ImmutableList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;


@Path("/companies")
@Produces(MediaType.APPLICATION_JSON)
public class CompaniesResource {

    @GET
    @Timed
    public List<Company> getAllCompanyInformation() {
        return ImmutableList.of(new Company("GOOG"));
    }
}



