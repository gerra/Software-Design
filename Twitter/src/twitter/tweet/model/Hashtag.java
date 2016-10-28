package twitter.tweet.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by root on 07.10.16.
 */
public class Hashtag {
    @SerializedName("indices")
    private int[] indices;
    @SerializedName("text")
    private String text;

    public Hashtag(int[] indices, String text) {
        this.indices = indices;
        this.text = text;
    }

    public int[] getIndices() {
        return indices;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hashtag hashtag = (Hashtag) o;

        if (!Arrays.equals(indices, hashtag.indices)) return false;
        return text != null ? text.equals(hashtag.text) : hashtag.text == null;

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(indices);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
