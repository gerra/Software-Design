package twitter;

import twitter.http.Error;
import twitter.http.HttpURLReader;
import twitter.tweet.*;
import twitter.tweet.model.Tweet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
//    Z2pLalY4eExqMnJKa3g4Z3FSWWNvWTloeDpYcjBjb05Ka210dG1wd1l3a2ZXTlhOamwyT1RacG5FV2l1NnB5YzdQSjFMT2Y5VkxZMA==
    public static void main(String[] args) throws IOException, ParseException {
        TweetParser tweetParser = new TweetParser();
        HttpURLReader httpUrlReader = new HttpURLReader();
        TweetFinder tweetFinder = new SecuredTweetFinder(
                "https", tweetParser, httpUrlReader, "Z2pLalY4eExqMnJKa3g4Z3FSWWNvWTloeDpYcjBjb05Ka210dG1wd1l3a2ZXTlhOamwyT1RacG5FV2l1NnB5YzdQSjFMT2Y5VkxZMA==", "api.twitter.com"
        );
        String hashTag = "аффинаж";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("[dd MMM, hh:mm]");
            for (Tweet tweet : tweetFinder.getNTweetsByHashtag(hashTag, 100)) {
                System.out.println(
                        formatter.format(Utils.getTwitterDateFormatter().parse(tweet.getCreatedAt())) + " " +
                        tweet.getUser().getName() +
                        ": " + "https://twitter.com/" + tweet.getUser().getIdStr() + "/status/" + tweet.getIdStr() + "\n" +
                        tweet.getText());
                System.out.println("_________________________________");
            }
            TweetManager tweetManager = new TweetManager(tweetFinder);
            for (int f : tweetManager.getTweetsFrequency(hashTag, 24)) {
                System.out.print(f + " ");
            }
            System.out.println();
        } catch (Error error) {
            System.err.println(error.getMessage() + "\n" + error.getBody());
        }
    }
}
