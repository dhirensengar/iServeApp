package com.cears.serviceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dhiren on 4/18/2017.
 */

public class SubmittedPreAnswer {

    @SerializedName("pre_qid")
    @Expose
    private String preQid;
    @SerializedName("pre_answer")
    @Expose
    private String preAnswer;

    public String getPreQid() {
        return preQid;
    }

    public void setPreQid(String preQid) {
        this.preQid = preQid;
    }

    public String getPreAnswer() {
        return preAnswer;
    }

    public void setPreAnswer(String preAnswer) {
        this.preAnswer = preAnswer;
    }

}
