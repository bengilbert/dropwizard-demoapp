package mergermarkets.service.stockprice;


import lombok.Value;
import mergermarkets.service.tickercode.TickerCode;

import java.net.URI;

@Value
public class StockPrice {
    final private TickerCode tickerCode;
    final private long latestPrice;
    final private String priceUnits;
    final private URI storyFeedUrl;

    public StockPrice(final TickerCode tickerCode, final long latestPrice, final String priceUnits, final URI storyFeedUrl) {
        this.tickerCode = tickerCode;
        this.latestPrice = latestPrice;
        this.priceUnits = priceUnits;
        this.storyFeedUrl = storyFeedUrl;
    }
}

//"tickerCode": "GOOG",
//        "latestPrice": 54407,
//        "priceUnits": "GBP:pence",
//        "asOf": "2015-05-06T15:05:59.912Z",
//        "storyFeedUrl": "http://mm-recruitment-story-feed-api.herokuapp.com/8271"
//        }