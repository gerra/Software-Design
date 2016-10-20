package twitter.tweet.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 19.10.16.
 */
public class User {
    @SerializedName("id")
    private long id;
    @SerializedName("id_str")
    private String idStr;
    @SerializedName("name")
    private String name;
    @SerializedName("location")
    private String location;

    public long getId() {
        return id;
    }

    public String getIdStr() {
        return idStr;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
