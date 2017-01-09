
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

}
