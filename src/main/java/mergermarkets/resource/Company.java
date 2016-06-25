package mergermarkets.resource;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Company {
    private String companyName;


    public Company() {
        // required for jackson
    }

    public Company(final String compnayName) {
        this.companyName = compnayName;
    }

    @JsonProperty
    public String companyName() {
        return companyName;
    }

//    - Company name
//    - Stock ticker code
//    - Stock price
//    - The two most recent news stories
//    - The sentiment (positive, negative or neutral) of each news story

}
