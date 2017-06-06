package com.cears.serviceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Dhiren on 4/18/2017.
 */

public class SectionNameResponse {


    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("data")
    @Expose
    private ArrayList<SectionNameSubResponse> data = null;

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

    public ArrayList<SectionNameSubResponse> getData() {
        return data;
    }

    public void setData(ArrayList<SectionNameSubResponse> data) {
        this.data = data;
    }

}
