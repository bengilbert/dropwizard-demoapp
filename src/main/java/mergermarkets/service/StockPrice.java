package mergermarkets.service;



public class StockPrice {



    final private TickerCode tickerCode;
    final private long latestPrice;
    final private String priceUnits;

    public StockPrice(final TickerCode tickerCode, final long latestPrice, final String priceUnits) {
        this.tickerCode = tickerCode;
        this.latestPrice = latestPrice;
        this.priceUnits = priceUnits;
    }

    public long getLatestPrice() {
        return this.latestPrice;
    }

    public String getPriceUnits() {
        return priceUnits;
    }

    public TickerCode getTickerCode() {
        return tickerCode;
    }


}

//"tickerCode": "GOOG",
//        "latestPrice": 54407,
//        "priceUnits": "GBP:pence",
//        "asOf": "2015-05-06T15:05:59.912Z",
//        "storyFeedUrl": "http://mm-recruitment-story-feed-api.herokuapp.com/8271"
//        }