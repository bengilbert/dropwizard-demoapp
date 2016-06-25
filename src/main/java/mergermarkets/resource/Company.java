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

}
