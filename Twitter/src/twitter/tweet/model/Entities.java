package twitter.tweet.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by root on 07.10.16.
 */
public class Entities {
    @SerializedName("hashtags")
    private Hashtag[] hashtags;

    public Entities(Hashtag[] hashtags) {
        this.hashtags = hashtags;
    }

    public Hashtag[] getHashtags() {
        return hashtags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entities entities = (Entities) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(hashtags, entities.hashtags);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(hashtags);
    }
}
