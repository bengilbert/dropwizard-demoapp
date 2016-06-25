package mergermarkets.dropwizard;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class AppConfig extends Configuration {

    @NotEmpty
    private String stockPriceUrl;

    @JsonProperty
    public String getStockPriceUrl() {
        return stockPriceUrl;
    }

}
