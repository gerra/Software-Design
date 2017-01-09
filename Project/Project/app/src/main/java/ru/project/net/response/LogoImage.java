
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

}
