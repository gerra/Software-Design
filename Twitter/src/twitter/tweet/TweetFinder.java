package twitter.tweet;

import twitter.http.Error;
import twitter.http.HttpURLReader;
import twitter.tweet.model.Tweet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract class TweetFinder {
    private String protocol;
    private String host;
    private TweetParser tweetParser;
    private HttpURLReader httpUrlReader;

    public TweetFinder(String protocol, String host, TweetParser tweetParser, HttpURLReader httpUrlReader) {
        this.protocol = protocol;
        this.host = host;
        this.tweetParser = tweetParser;
        this.httpUrlReader = httpUrlReader;
    }

    protected abstract InputStream getTweetListIS(String hashtag, int count) throws IOException, Error;

    public List<Tweet> getNTweetsByHashtag(String hashtag, int count) throws IOException, Error {
        return tweetParser.parseToListFromStream(getTweetListIS(hashtag, count));
    }

    protected HttpURLReader getHttpUrlReader() {
        return httpUrlReader;
    }

    protected TweetParser getTweetParser() {
        return tweetParser;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }
}
