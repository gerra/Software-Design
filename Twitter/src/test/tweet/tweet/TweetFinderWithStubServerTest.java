package test.tweet.tweet;

import com.xebialabs.restito.semantics.Action;
import com.xebialabs.restito.semantics.Condition;
import com.xebialabs.restito.server.StubServer;
import org.glassfish.grizzly.http.Method;
import org.junit.Assert;
import org.junit.Test;
import twitter.http.Error;
import twitter.http.HttpURLReader;
import twitter.tweet.SecuredTweetFinder;
import twitter.tweet.TweetParser;
import twitter.tweet.model.Tweet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.stringContent;

public class TweetFinderWithStubServerTest {
    private static final int PORT = 32455;
    private final SecuredTweetFinder securedTweetFinder = new SecuredTweetFinder(
            "http", new TweetParser(), new HttpURLReader(), "key", "localhost:" + Integer.toString(PORT)
    );

    @Test
    public void getNTweetsByHashtag() throws IOException {
        String mockedTweets;
        try (BufferedReader in = new BufferedReader(new FileReader("mock"))) {
            String singleLine;
            StringBuilder builder = new StringBuilder();
            while ((singleLine = in.readLine()) != null) {
                builder.append(singleLine);
                builder.append("\n");
            }
            mockedTweets = builder.toString();
        }

        withStubServer(PORT, s -> {
            whenHttp(s)
                    .match(Condition.method(Method.GET), Condition.startsWithUri("/1.1/search"))
                    .then(Action.ok(), stringContent(mockedTweets));

            whenHttp(s)
                    .match(Condition.method(Method.POST), Condition.startsWithUri("/oauth2"))
                    .then(Action.ok(), stringContent("{\"access_token\": \"lol\"}"));

            try {
                List<Tweet> tweets = securedTweetFinder.getNTweetsByHashtag("lol", 100);
                Assert.assertEquals(100, tweets.size());
            } catch (IOException | Error e) {
                e.printStackTrace();
                Assert.fail();
            }
        });
    }

    private void withStubServer(int port, Consumer<StubServer> callback) {
        StubServer stubServer = null;
        try {
            stubServer = new StubServer(port).run();
            callback.accept(stubServer);
        } finally {
            if (stubServer != null) {
                stubServer.stop();
            }
        }
    }
}
