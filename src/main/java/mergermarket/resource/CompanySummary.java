package mergermarket.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanySummary {

    public CompanySummary() {
        // required for jackson
    }

    @JsonProperty
    private String code;
}