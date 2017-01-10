
package ru.project.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Event {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("starts_at")
    @Expose
    private String startsAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("poster_image")
    @Expose
    private PosterImage posterImage;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("moderation_status")
    @Expose
    private String moderationStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(String startsAt) {
        this.startsAt = startsAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PosterImage getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(PosterImage posterImage) {
        this.posterImage = posterImage;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getModerationStatus() {
        return moderationStatus;
    }

    public void setModerationStatus(String moderationStatus) {
        this.moderationStatus = moderationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        if (getId() != event.getId()) return false;
        if (getStartsAt() != null ? !getStartsAt().equals(event.getStartsAt()) : event.getStartsAt() != null)
            return false;
        if (getName() != null ? !getName().equals(event.getName()) : event.getName() != null)
            return false;
        if (getUrl() != null ? !getUrl().equals(event.getUrl()) : event.getUrl() != null)
            return false;
        if (getPosterImage() != null ? !getPosterImage().equals(event.getPosterImage()) : event.getPosterImage() != null)
            return false;
        if (getCategories() != null ? !getCategories().equals(event.getCategories()) : event.getCategories() != null)
            return false;
        return getModerationStatus() != null ? getModerationStatus().equals(event.getModerationStatus()) : event.getModerationStatus() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getStartsAt() != null ? getStartsAt().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + (getPosterImage() != null ? getPosterImage().hashCode() : 0);
        result = 31 * result + (getCategories() != null ? getCategories().hashCode() : 0);
        result = 31 * result + (getModerationStatus() != null ? getModerationStatus().hashCode() : 0);
        return result;
    }
}
