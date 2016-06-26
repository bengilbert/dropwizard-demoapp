package mergermarkets.service;


import java.util.Optional;

public class StockPriceService {

    public Optional<StockPrice> getStockPriceForTickerCode(final String tickerCode) {
        return Optional.of(new StockPrice(tickerCode, 1, "units"));
    }
}


