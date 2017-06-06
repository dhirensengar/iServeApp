package com.cears.serviceapp.models;

/**
 * Created by Dhiren on 4/4/2017.
 */

public class LoginAuthModel {


    String id;
    String token;


    public LoginAuthModel(String id, String token) {
        this.id = id;
        this.token = token;
    }
}
