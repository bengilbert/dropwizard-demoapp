package mergermarkets.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import mergermarkets.service.NewsStory;
import mergermarkets.service.StockPrice;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class Company {
    private String companyName;
    private Optional<StockPrice> stockPrice = Optional.empty();
    private List<NewsStory> newsStories = Collections.emptyList();

    public Company() {
        // required for jackson
    }

    public Company(final String companyName) {
        this.companyName = companyName;
    }

    public void setStockPrice(final StockPrice stockPrice) {
        this.stockPrice = Optional.of(stockPrice);
    }

    public void setNewsStories(final List<NewsStory> newsStories) {
        this.newsStories = newsStories;
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

    @JsonProperty
    public List<CompanyNews> newsStories() {
        ModelMapper modelMapper = new ModelMapper();
        Type targetListType = new TypeToken<List<CompanyNews>>() {}.getType();
        return modelMapper.map(newsStories, targetListType);
    }

//    - Company name
//    - Stock ticker code
//    - Stock price
//    - The two most recent news stories
//    - The sentiment (positive, negative or neutral) of each news story

}
