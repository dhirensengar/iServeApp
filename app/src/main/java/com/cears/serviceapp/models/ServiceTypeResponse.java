package com.cears.serviceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Dhiren on 4/17/2017.
 */

public class ServiceTypeResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("data")
    @Expose
    private ArrayList<ServiceTypeSubResponse> data = null;

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

    public ArrayList<ServiceTypeSubResponse> getData() {
        return data;
    }

    public void setData(ArrayList<ServiceTypeSubResponse> data) {
        this.data = data;
    }


}
