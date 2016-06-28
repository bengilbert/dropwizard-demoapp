package mergermarkets.service.stockprice;

import mergermarkets.service.tickercode.TickerCode;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StockPriceTest {

    @Test
    public void textAfterColonIsReturnedForCorrectlyFormattedUnits() {
        StockPrice stockPrice = new StockPrice(new TickerCode("any"), 2345, "GBP:pence");
        assertThat(stockPrice.getPriceUnits(), is("pence"));
    }

    @Test
    public void emptyStringIsReturnedForInCorrectlyFormattedUnits() {
        StockPrice stockPrice = new StockPrice(new TickerCode("any"), 2345, "GBP:");
        assertThat(stockPrice.getPriceUnits(), is(""));

        StockPrice stockPrice2 = new StockPrice(new TickerCode("any"), 2345, "GBP");
        assertThat(stockPrice2.getPriceUnits(), is(""));

        StockPrice stockPrice3 = new StockPrice(new TickerCode("any"), 2345, "");
        assertThat(stockPrice3.getPriceUnits(), is(""));

        StockPrice stockPrice4 = new StockPrice(new TickerCode("any"), 2345, null);
        assertThat(stockPrice4.getPriceUnits(), is(""));
    }
}