package mergermarket.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Collections;
import java.util.List;

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class Company {
    private String companyName;
    private String stockPrice;
    private List<CompanyNews> newsStories = Collections.emptyList();

    public Company() {
        // required for jackson
    }

    public Company(final String companyName) {
        this.companyName = companyName;
    }

    public void setStockPrice(final String stockPrice) {
        this.stockPrice = stockPrice;
    }

    public void setNewsStories(final List<CompanyNews> newsStories) {
        // TODO limit to a maximum to two news stories
        this.newsStories = newsStories;
    }

    @JsonProperty
    public String companyName() {
        return companyName;
    }

    @JsonProperty
    public String stockPrice() {
        return stockPrice;
    }

    @JsonProperty
    public List<CompanyNews> newsStories() {
        return newsStories;
    }

//    - Company name
//    - Stock ticker code
//    - Stock price
//    - The two most recent news stories
//    - The sentiment (positive, negative or neutral) of each news story

}
