package mergermarkets.resource;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

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
