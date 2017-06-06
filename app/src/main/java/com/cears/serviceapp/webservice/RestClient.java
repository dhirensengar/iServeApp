package com.cears.serviceapp.webservice;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    //    private static final String API_BASE_URL = "http://deals4you.cearsinfotech.in/WebService/";

    private static final String API_BASE_URL = "http://iserve.cearsinfotech.in/WebService/";

    private static Api api;
    private static Context mContext;
    private static final RestClient restClient = new RestClient(mContext);

    static {
        initRestClient();
    }

    public RestClient(Context context) {
        this.mContext = context;
    }

    private static void initRestClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .readTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(2, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        api = retrofit.create(Api.class);
    }

    public RestClient getInstance() {
        return restClient;
    }

    public Api get() {
        return api;
    }
}
