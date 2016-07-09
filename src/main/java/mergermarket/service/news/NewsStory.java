package mergermarket.service.news;


import lombok.AllArgsConstructor;
import lombok.Value;
import mergermarket.service.sentiment.Sentiment;

@Value
@AllArgsConstructor
public class NewsStory {
    final private String headline;
    final private String body;
    final private Sentiment sentiment;
}
