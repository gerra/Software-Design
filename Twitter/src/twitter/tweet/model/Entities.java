package twitter.tweet.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 07.10.16.
 */
public class Entities {
    @SerializedName("hashtags")
    private Hashtag[] hashtags;

    public Hashtag[] getHashtags() {
        return hashtags;
    }
}
