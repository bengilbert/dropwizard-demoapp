package mergermarkets.resource;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
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
