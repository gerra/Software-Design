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

    public User(long id, String idStr, String name, String location) {
        this.id = id;
        this.idStr = idStr;
        this.name = name;
        this.location = location;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (idStr != null ? !idStr.equals(user.idStr) : user.idStr != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return location != null ? location.equals(user.location) : user.location == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (idStr != null ? idStr.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
