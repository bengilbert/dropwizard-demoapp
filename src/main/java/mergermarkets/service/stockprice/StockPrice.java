package mergermarkets.service.stockprice;

import lombok.Value;
import mergermarkets.service.tickercode.TickerCode;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Value
public class StockPrice {
    final private TickerCode tickerCode;
    final private long latestPrice;
    final private String priceUnits;
    final private Optional<URI> storyFeedUrl;

    public StockPrice(final TickerCode tickerCode, final long latestPrice, final String priceUnits, @NotNull final URI storyFeedUrl) {
        this.tickerCode = tickerCode;
        this.latestPrice = latestPrice;
        this.priceUnits = priceUnits;
        this.storyFeedUrl = Optional.of(storyFeedUrl);
    }

    public StockPrice(final TickerCode tickerCode, final long latestPrice, final String priceUnits) {
        this.tickerCode = tickerCode;
        this.latestPrice = latestPrice;
        this.priceUnits = priceUnits;
        this.storyFeedUrl = Optional.empty();
    }

    public String getPriceUnits() {
        if (isBlank(priceUnits) || priceUnits.split(":").length != 2) {
            return "";
        }

        return priceUnits.split(":")[1];
    }
}