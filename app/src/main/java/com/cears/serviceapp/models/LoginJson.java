package com.cears.serviceapp.models;

/**
 * Created by Dhiren on 3/24/2017.
 */

public class LoginJson {

    String service;
    LoginRequestModel request;
    LoginAuthModel auth;

    public LoginJson(String service, LoginRequestModel request) {
        this.service = service;
        this.request = request;
    }

    public LoginJson(String service, LoginRequestModel request, LoginAuthModel auth) {
        this.service = service;
        this.request = request;
        this.auth = auth;
    }

}
