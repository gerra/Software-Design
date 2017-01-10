
package ru.project.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("field_id")
    @Expose
    private String fieldId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("is_mandatory")
    @Expose
    private boolean isMandatory;
    @SerializedName("is_for_every_visitor")
    @Expose
    private boolean isForEveryVisitor;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public boolean isIsForEveryVisitor() {
        return isForEveryVisitor;
    }

    public void setIsForEveryVisitor(boolean isForEveryVisitor) {
        this.isForEveryVisitor = isForEveryVisitor;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        if (isMandatory != question.isMandatory) return false;
        if (isForEveryVisitor != question.isForEveryVisitor) return false;
        if (getFieldId() != null ? !getFieldId().equals(question.getFieldId()) : question.getFieldId() != null)
            return false;
        if (getName() != null ? !getName().equals(question.getName()) : question.getName() != null)
            return false;
        if (getComment() != null ? !getComment().equals(question.getComment()) : question.getComment() != null)
            return false;
        if (getType() != null ? !getType().equals(question.getType()) : question.getType() != null)
            return false;
        return getMeta() != null ? getMeta().equals(question.getMeta()) : question.getMeta() == null;

    }

    @Override
    public int hashCode() {
        int result = getFieldId() != null ? getFieldId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getComment() != null ? getComment().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (isMandatory ? 1 : 0);
        result = 31 * result + (isForEveryVisitor ? 1 : 0);
        result = 31 * result + (getMeta() != null ? getMeta().hashCode() : 0);
        return result;
    }
}
