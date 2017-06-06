package com.cears.serviceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dhiren on 4/15/2017.
 */

public class PreQuestionData {

    @SerializedName("pre_qid")
    @Expose
    private String preQid;
    @SerializedName("pre_question")
    @Expose
    private String preQuestion;
    @SerializedName("is_deleted")
    @Expose
    private String isDeleted;
    @SerializedName("creation_datetime")
    @Expose
    private String creationDatetime;
    @SerializedName("modification_datetime")
    @Expose
    private Object modificationDatetime;
    @SerializedName("deletion_datetime")
    @Expose
    private Object deletionDatetime;

    public String getPreQid() {
        return preQid;
    }

    public void setPreQid(String preQid) {
        this.preQid = preQid;
    }

    public String getPreQuestion() {
        return preQuestion;
    }

    public void setPreQuestion(String preQuestion) {
        this.preQuestion = preQuestion;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(String creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public Object getModificationDatetime() {
        return modificationDatetime;
    }

    public void setModificationDatetime(Object modificationDatetime) {
        this.modificationDatetime = modificationDatetime;
    }

    public Object getDeletionDatetime() {
        return deletionDatetime;
    }

    public void setDeletionDatetime(Object deletionDatetime) {
        this.deletionDatetime = deletionDatetime;
    }
}
