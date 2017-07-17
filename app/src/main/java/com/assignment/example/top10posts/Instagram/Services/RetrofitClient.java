package com.assignment.example.top10posts.Instagram.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by karel on 7/14/2017.
 */

public class RetrofitClient {

    private static RetrofitClient retrofitClient = null;
    private Retrofit retrofit = null;

    public static RetrofitClient getClient(String baseUrl) {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient(baseUrl);
        }
        return retrofitClient;
    }

    private RetrofitClient(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
