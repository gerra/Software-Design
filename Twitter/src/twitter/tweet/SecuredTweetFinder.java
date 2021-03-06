package twitter.tweet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import twitter.http.Error;
import twitter.http.HttpURLReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class SecuredTweetFinder extends TweetFinder {
    private String key;
    private String accessToken;

    public SecuredTweetFinder(String protocol, TweetParser tweetParser, HttpURLReader httpUrlReader, String key, String host) {
        super(protocol, host, tweetParser, httpUrlReader);
        this.key = key;
        this.accessToken = null;
    }

    @Override
    protected InputStream getTweetListIS(String hashtag, int count) throws IOException, Error {
        checkAuthorization();
        Map<String, String> headers = new HashMap<>();
        headers.put("Host", getHost());
        headers.put("User-Agent", "My Twitter App v1.0.23");
        headers.put("Authorization", "Bearer " + accessToken);
        headers.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        String query = "#" + hashtag;
        String requestUrl = getProtocol() + "://" + getHost() + "/1.1/search/tweets.json?q=" +
                URLEncoder.encode(query, "UTF-8") +
                "&count=" + Integer.toString(count);
        return getHttpUrlReader().sendGetRequestAndGetIS(requestUrl, headers);
    }

    private void checkAuthorization() throws IOException, Error {
        while (this.accessToken == null) {
            Map<String, String> headers = new HashMap<>();
            headers.put("Host", getHost());
            headers.put("User-Agent", "My Twitter App v1.0.23");
            headers.put("Authorization", "Basic " + key);
            headers.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            try {
                InputStream registerResponseIS = getHttpUrlReader()
                        .sendPostRequestAndGetIS(getProtocol() + "://" + getHost() + "/oauth2/token", headers, "grant_type=client_credentials");
                JsonObject jsonBody = (JsonObject) new JsonParser().parse(new InputStreamReader(registerResponseIS));
                JsonElement jsonAccessToken = jsonBody.get("access_token");
                if (jsonAccessToken != null) {
                    this.accessToken = jsonAccessToken.getAsString();
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
                throw new ConnectException("Check your internet connectivity");
            }
        }
    }
}
