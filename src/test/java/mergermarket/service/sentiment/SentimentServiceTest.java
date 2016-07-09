package mergermarket.service.sentiment;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SentimentServiceTest {

    final SentimentService sentimentService = new SentimentService();

    @Test
    public void aPositiveNewsStoryShouldHaveAPositiveSentiment() {
        final String positiveSentence = "positive, success, grow, gains, happy, healthy";
        assertThat(sentimentService.getSentiment(positiveSentence), is(Sentiment.POSITIVE));
    }

    @Test
    public void anEmptySentenceShouldHaveANeutralSentiment() {
        assertThat(sentimentService.getSentiment(""), is(Sentiment.NEUTRAL));
    }

    @Test
    public void aSentenceWithOnePositiveWordShouldHaveANeutralSentiment() {
        assertThat(sentimentService.getSentiment("a positive sentence"), is(Sentiment.NEUTRAL));
    }

    @Test
    public void aSentenceWithOneNegativeWordShouldHaveANegativeSentiment() {
        assertThat(sentimentService.getSentiment("a disappointing sentence"), is(Sentiment.NEGATIVE));
    }

    @Test
    public void aSentenceWithAnEqualNumberOfPositiveAndNegativeWordsShouldHaveNeutralSentiment() {
        assertThat(sentimentService.getSentiment("a disappointing happy sentence"), is(Sentiment.NEUTRAL));
    }

    @Test
    public void aSentenceWithTwoMorePositiveThanNegativeWordsShouldHavePositiveSentiment() {
        assertThat(sentimentService.getSentiment("a disappointing healthy happy sentence which is positive overall"), is(Sentiment.POSITIVE));
    }

    @Test
    public void sentimentCalculationIsCaseInsensitive() {
        final String positiveSentence = "POSITIVE, SUCCESS, GROW, GAINS, HAPPY, HEALTHY";

        assertThat(sentimentService.getSentiment(positiveSentence), is(Sentiment.POSITIVE));
    }
}