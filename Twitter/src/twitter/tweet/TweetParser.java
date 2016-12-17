package twitter.tweet;

import com.google.gson.*;
import twitter.tweet.model.Tweet;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TweetParser {
    public List<Tweet> parseToListFromStream(InputStream responseIS) {
        InputStreamReader isReader = new InputStreamReader(responseIS);
        JsonObject jsonBody = (JsonObject) new JsonParser().parse(isReader);
        JsonArray jsonStatuses = (JsonArray) jsonBody.get("statuses");

        Gson gson = new Gson();
        List<Tweet> tweets = new ArrayList<>();
        for (JsonElement jsonTweet : jsonStatuses) {
            Tweet tweet = gson.fromJson(jsonTweet, Tweet.class);
            tweets.add(tweet);
        }
        return tweets;
    }
}
