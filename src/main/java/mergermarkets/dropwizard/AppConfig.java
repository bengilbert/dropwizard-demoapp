package mergermarkets.dropwizard;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class AppConfig extends Configuration {

    @NotEmpty
    private String stockPriceUrl;

    @NotEmpty
    private String tickerCodeMongoDb;

    @JsonProperty
    public String getStockPriceUrl() {
        return stockPriceUrl;
    }

    @JsonProperty
    public String getTickerCodeMongoDb() {
        return tickerCodeMongoDb;
    }

}
