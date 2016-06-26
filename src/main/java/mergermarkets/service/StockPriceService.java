package mergermarkets.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class StockPriceService {
    private String stockPriceUrl;

    public StockPriceService(final String stockPriceUrl) {
        this.stockPriceUrl = stockPriceUrl;
    }

    public Optional<StockPrice> getStockPriceForTickerCode(final TickerCode tickerCode) {
        final HttpResponse<JsonNode> response;
        try {
            response = Unirest.get(stockPriceUrl + "/" + tickerCode.getCode()).asJson();

            if (response.getStatus() == 200) {
                final int latestPrice = response.getBody().getObject().getInt("latestPrice");
                final String units = response.getBody().getObject().getString("priceUnits");
                return Optional.of(new StockPrice(tickerCode, latestPrice, units));
            }
        } catch (UnirestException e) {
            log.warn("Unable to determine stock price for {}", tickerCode, e);
        }

        return Optional.empty();
    }
}


