package test.tweet.http;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import test.tweet.rule.HostReachableRule;
import twitter.http.Error;
import twitter.http.HttpURLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class HttpURLReaderTest {
    private static final String google = "https://google.com";
    private static final String facebook = "https://facebook.com";
    private static final String twitter = "https://twitter.com";
    private static final String badHost = "https://q";

    @Rule
    public HostReachableRule hostReachableRule = new HostReachableRule();

    private static HttpURLReader httpURLReader;

    @BeforeClass
    public static void setUp() {
        httpURLReader = new HttpURLReader();
    }

    private void testGetWithoutCheckReachable(String host) throws IOException, Error {
        try (InputStream is = httpURLReader.sendGetRequestAndGetIS(host, null)) {
            Assert.assertTrue(is.read() > 0);
        }
    }

    @Test
    @HostReachableRule.HostReachable(google)
    public void testGetGoogle() throws IOException, Error {
        testGetWithoutCheckReachable(google);
    }

    @Test
    @HostReachableRule.HostReachable(facebook)
    public void testGetFacebook() throws IOException, Error {
        testGetWithoutCheckReachable(facebook);
    }

    @Test
    @HostReachableRule.HostReachable(twitter)
    public void testGetTwitter() throws IOException, Error {
        testGetWithoutCheckReachable(twitter);
    }

    @Test
    @HostReachableRule.HostReachable(badHost)
    public void testGetBadHost() throws IOException, Error {
        testGetWithoutCheckReachable(badHost);
    }

    @Test(expected = UnknownHostException.class)
    public void testGetBadHostWithException() throws IOException, Error {
        testGetWithoutCheckReachable(badHost);
    }

    @Test
    @HostReachableRule.HostReachable("requestb.in")
    public void testSendPostRequestAndGetIS() {
        String host = "http://requestb.in/1c24ekm1";
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        try(BufferedReader is = new BufferedReader(new InputStreamReader(httpURLReader.sendPostRequestAndGetIS(host, headers, "Hello!")))) {
            String response = is.readLine();
            Assert.assertEquals(response, "ok");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }

    }
}