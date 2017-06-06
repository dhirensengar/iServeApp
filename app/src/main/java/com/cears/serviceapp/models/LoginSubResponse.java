package com.cears.serviceapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dhiren on 4/13/2017.
 */

public class LoginSubResponse {

    @SerializedName("user_info")
    @Expose
    private LoginUserinfo userInfo;

    public LoginUserinfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(LoginUserinfo userInfo) {
        this.userInfo = userInfo;
    }
}
