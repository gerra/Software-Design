
package ru.project.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogoImage {

    @SerializedName("default_url")
    @Expose
    private String defaultUrl;
    @SerializedName("uploadcare_url")
    @Expose
    private String uploadcareUrl;

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public String getUploadcareUrl() {
        return uploadcareUrl;
    }

    public void setUploadcareUrl(String uploadcareUrl) {
        this.uploadcareUrl = uploadcareUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogoImage)) return false;

        LogoImage logoImage = (LogoImage) o;

        if (getDefaultUrl() != null ? !getDefaultUrl().equals(logoImage.getDefaultUrl()) : logoImage.getDefaultUrl() != null)
            return false;
        return getUploadcareUrl() != null ? getUploadcareUrl().equals(logoImage.getUploadcareUrl()) : logoImage.getUploadcareUrl() == null;

    }

    @Override
    public int hashCode() {
        int result = getDefaultUrl() != null ? getDefaultUrl().hashCode() : 0;
        result = 31 * result + (getUploadcareUrl() != null ? getUploadcareUrl().hashCode() : 0);
        return result;
    }
}
