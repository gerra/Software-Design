
package ru.project.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Button {

    @SerializedName("code_html")
    @Expose
    private String codeHtml;

    public String getCodeHtml() {
        return codeHtml;
    }

    public void setCodeHtml(String codeHtml) {
        this.codeHtml = codeHtml;
    }

}
