package twitter.tweet.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 07.10.16.
 */
public class Tweet {
    @SerializedName("id")
    private long id;
    @SerializedName("id_str")
    private String idStr;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("text")
    private String text;
    @SerializedName("user")
    private User user;
    @SerializedName("entities")
    private Entities entities;

    public long getId() {
        return id;
    }

    public String getIdStr() {
        return idStr;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public Entities getEntities() {
        return entities;
    }
}
