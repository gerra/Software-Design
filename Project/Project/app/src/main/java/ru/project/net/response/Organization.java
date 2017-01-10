
package ru.project.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Organization {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description_html")
    @Expose
    private String descriptionHtml;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("logo_image")
    @Expose
    private LogoImage logoImage;
    @SerializedName("subdomain")
    @Expose
    private String subdomain;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LogoImage getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(LogoImage logoImage) {
        this.logoImage = logoImage;
    }

    public String getSubdomain() {
        return subdomain;
    }

    public void setSubdomain(String subdomain) {
        this.subdomain = subdomain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;

        Organization that = (Organization) o;

        if (getId() != that.getId()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;
        if (getDescriptionHtml() != null ? !getDescriptionHtml().equals(that.getDescriptionHtml()) : that.getDescriptionHtml() != null)
            return false;
        if (getUrl() != null ? !getUrl().equals(that.getUrl()) : that.getUrl() != null)
            return false;
        if (getLogoImage() != null ? !getLogoImage().equals(that.getLogoImage()) : that.getLogoImage() != null)
            return false;
        return getSubdomain() != null ? getSubdomain().equals(that.getSubdomain()) : that.getSubdomain() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescriptionHtml() != null ? getDescriptionHtml().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + (getLogoImage() != null ? getLogoImage().hashCode() : 0);
        result = 31 * result + (getSubdomain() != null ? getSubdomain().hashCode() : 0);
        return result;
    }
}
