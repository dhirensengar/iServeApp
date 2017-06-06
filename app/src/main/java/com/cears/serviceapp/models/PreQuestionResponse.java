package com.cears.serviceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Dhiren on 4/15/2017.
 */

public class PreQuestionResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("data")
    @Expose
    private ArrayList<PreQuestionData> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<PreQuestionData> getData() {
        return data;
    }

    public void setData(ArrayList<PreQuestionData> data) {
        this.data = data;
    }

}
