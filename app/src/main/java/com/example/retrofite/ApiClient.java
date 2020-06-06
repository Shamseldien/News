package com.example.retrofite;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String ROOT_URL = "https://newsapi.org/v2/";
    private static  ApiClient apiclient ;
    private static Retrofit retrofit ;

    private ApiClient(){
        retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static synchronized ApiClient getInstance(){
        if (apiclient == null){
            apiclient = new ApiClient();
        }
        return apiclient;
    }
    public ApiInterface getApi(){
        return retrofit.create(ApiInterface.class);
    }

}
