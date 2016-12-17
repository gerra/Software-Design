package test.tweet.tweet;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import test.tweet.rule.HostReachableRule;
import twitter.http.Error;
import twitter.http.HttpURLReader;
import twitter.tweet.SecuredTweetFinder;
import twitter.tweet.TweetFinder;
import twitter.tweet.TweetManager;
import twitter.tweet.TweetParser;
import twitter.tweet.model.Tweet;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@HostReachableRule.HostReachable("api.twitter.com")
public class SecuredTweetFinderTest {

    @ClassRule
    public static HostReachableRule hostReachableRule = new HostReachableRule();

    private static TweetFinder tweetFinder;
    private static TweetManager tweetManager;

    @BeforeClass
    public static void setUp() {
        TweetParser tweetParser = new TweetParser();
        HttpURLReader httpURLReader = new HttpURLReader();
        tweetFinder = new SecuredTweetFinder("https", tweetParser, httpURLReader, "Z2pLalY4eExqMnJKa3g4Z3FSWWNvWTloeDpYcjBjb05Ka210dG1wd1l3a2ZXTlhOamwyT1RacG5FV2l1NnB5YzdQSjFMT2Y5VkxZMA==", "api.twitter.com");
        tweetManager = new TweetManager(tweetFinder);
    }

    private Random random = new Random();

    private String nextHashtag() {
        int len = random.nextInt(10) + 1;
        String hashtag = "";
        for (int i = 0; i < len; i++) {
            hashtag += (char) ('a' + random.nextInt(26));
        }
        return hashtag;
    }

    @Test
    public void testGetTweetList() throws IOException, Error {
        List<Tweet> tweets;
        String hashtag;
        for (int i = 0; i < 10; i++) {
            hashtag = nextHashtag();
            tweets = tweetFinder.getNTweetsByHashtag(hashtag, 20);
            Assert.assertTrue(tweets.size() >= 0 && tweets.size() <= 20);
        }
        tweets = tweetFinder.getNTweetsByHashtag("java", 100);
        Assert.assertTrue(tweets.size() >= 0);
    }

    @Test
    public void testGetTweetsFrequency() throws IOException, Error {
        for (int i = 0; i < 10; i++) {
            String hashTag = nextHashtag();
            int n = random.nextInt(24) + 1;
            int[] frequencies = tweetManager.getTweetsFrequency(hashTag, n);
            Assert.assertTrue(frequencies.length == n);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTweetsFrequencyWithException() throws IOException, Error {
        tweetManager.getTweetsFrequency("java", random.nextInt());
    }
}