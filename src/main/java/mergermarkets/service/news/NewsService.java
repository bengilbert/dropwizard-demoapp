package mergermarkets.service.news;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class NewsService {

    public List<NewsStory> getNewsStories(final URI newsUrl) {
        log.debug("Retriving news stores from {}", newsUrl);
        final List<NewsStory> newsStories = new ArrayList<>();
        try {
            final HttpResponse<JsonNode> response = Unirest.get(newsUrl.toString()).asJson();
            if (response.getStatus() == 200) {
                final JSONArray newsStoriesArray = response.getBody().getArray();
                for (int i = 0; i < newsStoriesArray.length(); ++i) {
                    final JSONObject jsonStory = newsStoriesArray.getJSONObject(i);
                    final String headline = jsonStory.getString("headline");
                    final String body = jsonStory.getString("body");

                    newsStories.add(new NewsStory(headline, body));
                }
            }
        } catch (UnirestException e) {
            log.warn("Unable to request news stories from {}", newsUrl, e);
        }

        return newsStories;
    }

}
