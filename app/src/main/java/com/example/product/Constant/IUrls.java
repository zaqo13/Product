package com.example.product.Constant;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IUrls {


   public static final String BASE_URL = "http://xyz.in/abc/";


    public static Retrofit getRetrofitClient(String baseUrl) {

        Retrofit builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return builder;


    }

}
