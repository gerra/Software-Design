
package ru.project.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("blocked")
    @Expose
    private boolean blocked;
    @SerializedName("mandatory")
    @Expose
    private boolean mandatory;
    @SerializedName("block")
    @Expose
    private boolean block;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meta)) return false;

        Meta meta = (Meta) o;

        if (isBlocked() != meta.isBlocked()) return false;
        if (isMandatory() != meta.isMandatory()) return false;
        if (isBlock() != meta.isBlock()) return false;
        return getComment() != null ? getComment().equals(meta.getComment()) : meta.getComment() == null;

    }

    @Override
    public int hashCode() {
        int result = getComment() != null ? getComment().hashCode() : 0;
        result = 31 * result + (isBlocked() ? 1 : 0);
        result = 31 * result + (isMandatory() ? 1 : 0);
        result = 31 * result + (isBlock() ? 1 : 0);
        return result;
    }
}
