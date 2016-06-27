package mergermarkets.resource;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CompanyTest {

    @Test
    public void newsStoriesAreMapped() {
        Company company = new Company("Google Inc");
        company.setNewsStories(ImmutableList.of(new CompanyNews("headline1", "body1", "POSITIVE"), new CompanyNews("headline2", "body2", "NEUTRAL")));

        assertThat(company.newsStories().size(), is(2));
        assertThat(company.newsStories().get(0).getBody(), is("body1"));
        assertThat(company.newsStories().get(0).getHeadline(), is("headline1"));
        assertThat(company.newsStories().get(0).getSentiment(), is("POSITIVE"));
    }

}