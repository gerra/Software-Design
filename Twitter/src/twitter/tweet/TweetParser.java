package twitter.tweet;

import com.google.gson.*;
import twitter.tweet.model.Tweet;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 07.10.16.
 */
public class TweetParser {
    public List<Tweet> parseToListFromString(InputStream responseIS) {
        InputStreamReader isReader = new InputStreamReader(responseIS);
        JsonObject jsonBody = (JsonObject) new JsonParser().parse(isReader);
        JsonArray jsonStatuses = (JsonArray) jsonBody.get("statuses");

        Gson gson = new Gson();
        List<Tweet> tweets = new ArrayList<>();
        for (JsonElement jsonTweet : jsonStatuses) {
            Tweet tweet = gson.fromJson(jsonTweet, Tweet.class);
            tweets.add(tweet);
//            System.out.println(tweet.getText());
        }
        return tweets;
    }
}
