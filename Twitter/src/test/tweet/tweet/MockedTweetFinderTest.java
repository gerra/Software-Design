package test.tweet.tweet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import twitter.http.Error;
import twitter.tweet.TweetFinder;
import twitter.tweet.TweetManager;
import twitter.tweet.TweetParser;
import twitter.tweet.model.Entities;
import twitter.tweet.model.Hashtag;
import twitter.tweet.model.Tweet;
import twitter.tweet.model.User;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

public class MockedTweetFinderTest {

    @Mock
    private TweetFinder tweetFinder;

    private TweetManager tweetManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tweetManager = new TweetManager(tweetFinder);
    }

    @Test
    public void testGetNTweetsByHashtag() throws IOException, Error {
        when(tweetFinder.getNTweetsByHashtag("mock", 1))
                .thenReturn(Collections.singletonList(
                        new Tweet(
                                123456789,
                                "123456789",
                                "Fri Oct 21 20:57:32 +0000 2016",
                                "I'm super mock text! #mock",
                                new User(123456789, "123456789", "SuperMegaMock", "St. Petersburg"),
                                new Entities(new Hashtag[] {new Hashtag(new int[] {1, 5}, "mock")}))));
        List<Tweet> mockedTweets = new TweetParser()
                .parseToListFromStream(new BufferedInputStream(new FileInputStream("mock3")));
        List<Tweet> tweets = tweetFinder.getNTweetsByHashtag("mock", 1);
        Assert.assertEquals(mockedTweets, tweets);
    }

    @Test
    public void testGetTweetsFrequency() throws IOException, Error {
        when(tweetFinder.getNTweetsByHashtag("mock", 1))
                .thenReturn(Collections.singletonList(
                        new Tweet(
                                123456789,
                                "123456789",
                                "Fri Oct 21 20:57:32 +0000 2016",
                                "I'm super mock text! #mock",
                                new User(123456789, "123456789", "SuperMegaMock", "St. Petersburg"),
                                new Entities(new Hashtag[] {new Hashtag(new int[] {1, 5}, "mock")}))));

        int[] frequencies = tweetManager.getTweetsFrequency("mock", 4);
        Assert.assertEquals(4, frequencies.length);
        Assert.assertArrayEquals(frequencies, new int[] {0,0,0,0});
    }
}
