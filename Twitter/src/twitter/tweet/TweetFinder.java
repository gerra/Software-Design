package twitter.tweet;

import twitter.http.Error;
import twitter.http.URLReader;
import twitter.tweet.model.Tweet;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class TweetFinder {
    private TweetParser tweetParser;
    private URLReader urlReader;

    public TweetFinder(TweetParser tweetParser, URLReader urlReader) {
        this.tweetParser = tweetParser;
        this.urlReader = urlReader;
    }

    protected abstract InputStream getTweetListIS(String hashtag) throws IOException, Error;

    protected abstract InputStream getTweetListIS(String hashtag, int count) throws IOException, Error;

    public List<Tweet> getNTweetsByHashtag(String hashtag, int count) throws IOException, Error {
        return tweetParser.parseToListFromString(getTweetListIS(hashtag, count));
    }

    public int[] getTweetsFrequency(String hashtag, int n) throws IOException, Error {
        List<Tweet> tweets = getNTweetsByHashtag(hashtag, 100);
        Date requestDate = new Date(); // after request because of slow internet speed
        int[] frequency = new int[n];
        for (Tweet tweet : tweets) {
            Date tweetDate = new Date();
            try {
                tweetDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy").parse(tweet.getCreatedAt());
            } catch (ParseException ignored) {
            }
//            System.out.println(tweetDate.toString() + " " + tweet.getText());
            long diff = requestDate.getTime() - tweetDate.getTime();
            int diffHour = (int) (diff / 1000 / 60 / 60);
            if (diffHour < n) {
                frequency[diffHour]++;
            }
        }
        return frequency;
    }

    protected URLReader getUrlReader() {
        return urlReader;
    }

    protected TweetParser getTweetParser() {
        return tweetParser;
    }
}
