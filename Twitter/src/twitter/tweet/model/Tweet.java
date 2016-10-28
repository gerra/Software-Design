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

    public Tweet(long id, String idStr, String createdAt, String text, User user, Entities entities) {
        this.id = id;
        this.idStr = idStr;
        this.createdAt = createdAt;
        this.text = text;
        this.user = user;
        this.entities = entities;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        if (id != tweet.id) return false;
        if (idStr != null ? !idStr.equals(tweet.idStr) : tweet.idStr != null) return false;
        if (createdAt != null ? !createdAt.equals(tweet.createdAt) : tweet.createdAt != null) return false;
        if (text != null ? !text.equals(tweet.text) : tweet.text != null) return false;
        if (user != null ? !user.equals(tweet.user) : tweet.user != null) return false;
        return entities != null ? entities.equals(tweet.entities) : tweet.entities == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (idStr != null ? idStr.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (entities != null ? entities.hashCode() : 0);
        return result;
    }
}
