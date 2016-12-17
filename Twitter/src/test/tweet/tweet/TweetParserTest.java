package test.tweet.tweet;

import org.junit.Assert;
import org.junit.Test;
import twitter.tweet.TweetParser;
import twitter.tweet.model.Entities;
import twitter.tweet.model.Hashtag;
import twitter.tweet.model.Tweet;
import twitter.tweet.model.User;

import java.io.FileInputStream;
import java.util.List;

/**
 * Created by root on 22.10.16.
 */
public class TweetParserTest {

    @Test
    public void testParseToListFromString() throws Exception {
        TweetParser parser = new TweetParser();
        List<Tweet> tweetList = parser.parseToListFromStream(new FileInputStream("mock3"));
        Assert.assertNotNull(tweetList);
        Assert.assertEquals(tweetList.size(), 1);
        Tweet tweet = tweetList.get(0);
        Assert.assertNotNull(tweet);
        Assert.assertEquals(tweet.getCreatedAt(), "Fri Oct 21 20:57:32 +0000 2016");
        Assert.assertEquals(tweet.getIdStr(), "123456789");
        Assert.assertEquals(tweet.getId(), 123456789);
        Assert.assertEquals(tweet.getText(), "I'm super mock text! #mock");
        User user = tweet.getUser();
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getIdStr(), "123456789");
        Assert.assertEquals(user.getId(), 123456789);
        Assert.assertEquals(user.getLocation(), "St. Petersburg");
        Assert.assertEquals(user.getName(), "SuperMegaMock");
        Entities entities = tweet.getEntities();
        Assert.assertNotNull(entities);
        Hashtag[] hashtags = entities.getHashtags();
        Assert.assertNotNull(hashtags);
        Assert.assertEquals(hashtags.length, 1);
        Hashtag hashtag = hashtags[0];
        Assert.assertNotNull(hashtag);
        Assert.assertEquals(hashtag.getText(), "mock");
        int[] indicies = hashtag.getIndices();
        Assert.assertNotNull(indicies);
        Assert.assertArrayEquals(indicies, new int[] {1, 5});
    }
}