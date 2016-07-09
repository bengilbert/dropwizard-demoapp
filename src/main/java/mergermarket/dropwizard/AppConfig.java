package mergermarket.dropwizard;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.bundles.assets.AssetsBundleConfiguration;
import io.dropwizard.bundles.assets.AssetsConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AppConfig extends Configuration implements AssetsBundleConfiguration {

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

    @Valid
    @NotNull
    @JsonProperty
    private final AssetsConfiguration assets = new AssetsConfiguration();

    @Override
    public AssetsConfiguration getAssetsConfiguration() {
        return assets;
    }

}
