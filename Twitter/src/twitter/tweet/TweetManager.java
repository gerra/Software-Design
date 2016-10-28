package twitter.tweet;

import twitter.http.Error;
import twitter.tweet.model.Tweet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class TweetManager {
    private TweetFinder tweetFinder;

    public TweetManager(TweetFinder tweetFinder) {
        this.tweetFinder = tweetFinder;
    }

    public int[] getTweetsFrequency(String hashtag, int n) throws IOException, Error {
        if (n < 1 || n > 24) {
            throw new IllegalArgumentException("N must be not great then 24 and not less than 1");
        }
        List<Tweet> tweets = tweetFinder.getNTweetsByHashtag(hashtag, 100);
        Date requestDate = new Date(); // after request because of slow internet speed
        int[] frequency = new int[n];
        for (Tweet tweet : tweets) {
            Date tweetDate = new Date();
            try {
                tweetDate = Utils.getTwitterDateFormatter().parse(tweet.getCreatedAt());
            } catch (ParseException ignored) {
            }
            long diff = requestDate.getTime() - tweetDate.getTime();
            int diffHour = (int) (diff / 1000 / 60 / 60);
            if (diffHour < n) {
                frequency[diffHour]++;
            }
        }
        return frequency;
    }
}
