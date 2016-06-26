package mergermarkets.resource;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompanyNews {

    public CompanyNews() {
        // for jackson
    }

    @JsonProperty
    private String headline;

    @JsonProperty
    private String body;
}
