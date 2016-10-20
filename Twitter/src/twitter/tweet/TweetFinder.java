package twitter.tweet;

import twitter.http.Error;
import twitter.http.URLReader;
import twitter.tweet.model.Tweet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract class TweetFinder {
    private TweetParser tweetParser;
    private URLReader urlReader;

    public TweetFinder(TweetParser tweetParser, URLReader urlReader) {
        this.tweetParser = tweetParser;
        this.urlReader = urlReader;
    }

    protected abstract InputStream getTweetListIS(String hashtag) throws IOException, Error;

    public List<Tweet> getTweetsByHashtag(String hashtag) throws IOException, Error {
        return tweetParser.parseToListFromString(getTweetListIS(hashtag));
    }

    protected URLReader getUrlReader() {
        return urlReader;
    }

    protected TweetParser getTweetParser() {
        return tweetParser;
    }
}
