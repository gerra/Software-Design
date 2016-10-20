package twitter;

import twitter.http.Error;
import twitter.http.URLReader;
import twitter.tweet.SecuredTweetFinder;
import twitter.tweet.TweetFinder;
import twitter.tweet.TweetParser;
import twitter.tweet.model.Tweet;

import java.io.IOException;

public class Main {
//    Z2pLalY4eExqMnJKa3g4Z3FSWWNvWTloeDpYcjBjb05Ka210dG1wd1l3a2ZXTlhOamwyT1RacG5FV2l1NnB5YzdQSjFMT2Y5VkxZMA==
    public static void main(String[] args) throws IOException {
        TweetParser tweetParser = new TweetParser();
        URLReader urlReader = new URLReader();
        TweetFinder tweetFinder = new SecuredTweetFinder(tweetParser, urlReader, "Z2pLalY4eExqMnJKa3g4Z3FSWWNvWTloeDpYcjBjb05Ka210dG1wd1l3a2ZXTlhOamwyT1RacG5FV2l1NnB5YzdQSjFMT2Y5VkxZMA==");
        String hashTag = "java";
        try {
            for (Tweet tweet : tweetFinder.getTweetsByHashtag(hashTag + "#job")) {
                System.out.println(tweet.getUser().getName() +
                        ": " + "https://twitter.com/" + tweet.getUser().getIdStr() + "/status/" + tweet.getIdStr() + "\n" +
                        tweet.getText());
                System.out.println("_________________________________");
            }
        } catch (Error error) {
            System.err.println(error.getMessage() + "\n" + error.getBody());
        }
    }
}
