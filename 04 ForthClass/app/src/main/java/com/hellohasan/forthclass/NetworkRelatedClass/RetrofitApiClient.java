package com.hellohasan.forthclass.NetworkRelatedClass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient {

    //This is my IP of localhost. you can use IP of your local machine or real server
    private static final String BASE_URL = "http://192.168.0.105/";

    //Access modifier of `retrofit` object is private. So no one can directly access it outside of this class
    private static Retrofit retrofit = null;

    //Gson converts our POJO object to JSON object and vice versa
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    //Only one Retrofit instance will be exist in our App
    //So first time the IF block will be executed but next times will return existing instance
    public static synchronized Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}