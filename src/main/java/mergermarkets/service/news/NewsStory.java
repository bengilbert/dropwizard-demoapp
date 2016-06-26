package mergermarkets.service.news;


import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class NewsStory {
    final private String headline;
    final private String body;
}
