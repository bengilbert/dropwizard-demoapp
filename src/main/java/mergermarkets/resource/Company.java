package mergermarkets.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import mergermarkets.service.StockPrice;

import java.util.Optional;


public class Company {
    private String companyName;
    private Optional<StockPrice> stockPrice = Optional.empty();


    public Company() {
        // required for jackson
    }

    public Company(final String companyName) {
        this.companyName = companyName;
    }

    public void setStockPrice(final StockPrice stockPrice) {
        this.stockPrice = Optional.of(stockPrice);
    }

    @JsonProperty
    public String companyName() {
        return companyName;
    }

    @JsonProperty
    public long stockPrice() {
        if (stockPrice.isPresent()) {
            return stockPrice.get().getLatestPrice();
        }

        return -1;
    }

//    - Company name
//    - Stock ticker code
//    - Stock price
//    - The two most recent news stories
//    - The sentiment (positive, negative or neutral) of each news story

}
