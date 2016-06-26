package mergermarkets.service.sentiment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentimentService {

    public Sentiment getSentiment(final String sentence) {

        int positiveWordCount = countRegexGroupMatches("(positive|success|grow|gains|happy|healthy)", sentence);
        int negativeWordCount = countRegexGroupMatches("(disappointing|concerns|decline|drag|slump|feared)", sentence);

        final int positivity = positiveWordCount - negativeWordCount;

        if (positivity < 0) {
            return Sentiment.NEGATIVE;
        } else if (positivity < 2) {
            return Sentiment.NEUTRAL;
        }
        return Sentiment.POSITIVE;
    }

    private int countRegexGroupMatches(final String regex, final String textToMatch) {
        Pattern positivePattern = Pattern.compile(regex);
        Matcher matcher = positivePattern.matcher(textToMatch.toLowerCase());

        int wordCount = 0;
        while (matcher.find()) {
            ++wordCount;
        }

        return wordCount;
    }

}