package mergermarkets.service;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StockPriceServiceTest {
    @Test
    public void shouldBeAbleToRetriveStockPriceForKnownTickerCode() {

        StockPriceService stockPriceService = new StockPriceService();
        Optional<StockPrice> stockPrice = stockPriceService.getStockPriceForTickerCode(new TickerCode("GOOG"));

        assertThat(stockPrice.isPresent(), is(true));
    }
}