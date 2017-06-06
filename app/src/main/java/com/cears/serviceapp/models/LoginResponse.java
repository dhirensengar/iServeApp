package com.cears.serviceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dhiren on 4/13/2017.
 */


public class LoginResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("secret_log_id")
    @Expose
    private String secretLogId;
    @SerializedName("data")
    @Expose
    private LoginSubResponse data;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecretLogId() {
        return secretLogId;
    }

    public void setSecretLogId(String secretLogId) {
        this.secretLogId = secretLogId;
    }

    public LoginSubResponse getData() {
        return data;
    }

    public void setData(LoginSubResponse data) {
        this.data = data;
    }


}
