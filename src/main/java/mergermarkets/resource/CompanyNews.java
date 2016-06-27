package mergermarkets.resource;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyNews {

    public CompanyNews() {
        // for jackson
    }

    @JsonProperty
    private String headline;

    @JsonProperty
    private String body;

    @JsonProperty
    private String sentiment;
}
