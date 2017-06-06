package com.cears.serviceapp.models;

/**
 * Created by Dhiren on 3/24/2017.
 */

public class LoginRequestModel {

    String email_id, password;
    String value;
    int opt;

    public LoginRequestModel(String email_id, String password) {
        this.email_id = email_id;
        this.password = password;
    }

    public LoginRequestModel(String value) {
        this.value = value;
    }

    public LoginRequestModel(int opt) {
        this.opt = opt;
    }
}
