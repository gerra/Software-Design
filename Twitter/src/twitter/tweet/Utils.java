package twitter.tweet;

import java.text.SimpleDateFormat;

public class Utils {
    private static final SimpleDateFormat twitterDateFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy");

    public static SimpleDateFormat getTwitterDateFormatter() {
        return twitterDateFormatter;
    }
}
