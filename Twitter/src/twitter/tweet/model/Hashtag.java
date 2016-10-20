package twitter.tweet.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 07.10.16.
 */
public class Hashtag {
    @SerializedName("indices")
    private int[] indices;
    @SerializedName("text")
    private String text;

    public int[] getIndices() {
        return indices;
    }

    public String getText() {
        return text;
    }
}
