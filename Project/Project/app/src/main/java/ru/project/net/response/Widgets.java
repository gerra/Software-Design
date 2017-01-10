
package ru.project.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Widgets {

    @SerializedName("custom")
    @Expose
    private Custom custom;
    @SerializedName("button")
    @Expose
    private Button button;

    public Custom getCustom() {
        return custom;
    }

    public void setCustom(Custom custom) {
        this.custom = custom;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Widgets)) return false;

        Widgets widgets = (Widgets) o;

        if (getCustom() != null ? !getCustom().equals(widgets.getCustom()) : widgets.getCustom() != null)
            return false;
        return getButton() != null ? getButton().equals(widgets.getButton()) : widgets.getButton() == null;

    }

    @Override
    public int hashCode() {
        int result = getCustom() != null ? getCustom().hashCode() : 0;
        result = 31 * result + (getButton() != null ? getButton().hashCode() : 0);
        return result;
    }
}
