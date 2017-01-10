
package ru.project.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Custom {

    @SerializedName("code_html")
    @Expose
    private String codeHtml;

    public String getCodeHtml() {
        return codeHtml;
    }

    public void setCodeHtml(String codeHtml) {
        this.codeHtml = codeHtml;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Custom)) return false;

        Custom custom = (Custom) o;

        return getCodeHtml() != null ? getCodeHtml().equals(custom.getCodeHtml()) : custom.getCodeHtml() == null;

    }

    @Override
    public int hashCode() {
        return getCodeHtml() != null ? getCodeHtml().hashCode() : 0;
    }
}
