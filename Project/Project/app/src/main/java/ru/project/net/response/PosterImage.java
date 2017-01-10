
package ru.project.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PosterImage {

    @SerializedName("default_url")
    @Expose
    private String defaultUrl;
    @SerializedName("uploadcare_url")
    @Expose
    private String uploadcareUrl;

    public String getDefaultUrl() {
        return generateHttpsPrefix(defaultUrl);
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public String getUploadcareUrl() {
        return generateHttpsPrefix(uploadcareUrl);
    }

    public void setUploadcareUrl(String uploadcareUrl) {
        this.uploadcareUrl = uploadcareUrl;
    }

    private static String generateHttpsPrefix(String url) {
        if (url != null && !url.startsWith("http")) {
            url = "https:" + url;
        }
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PosterImage)) return false;

        PosterImage that = (PosterImage) o;

        if (getDefaultUrl() != null ? !getDefaultUrl().equals(that.getDefaultUrl()) : that.getDefaultUrl() != null)
            return false;
        return getUploadcareUrl() != null ? getUploadcareUrl().equals(that.getUploadcareUrl()) : that.getUploadcareUrl() == null;

    }

    @Override
    public int hashCode() {
        int result = getDefaultUrl() != null ? getDefaultUrl().hashCode() : 0;
        result = 31 * result + (getUploadcareUrl() != null ? getUploadcareUrl().hashCode() : 0);
        return result;
    }
}
