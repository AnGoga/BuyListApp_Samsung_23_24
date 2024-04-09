package com.angoga.buylistapp.network.services;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit INSTANCE;

    public synchronized static Retrofit getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Retrofit.Builder()
                    .client(new OkHttpClient())
                    .baseUrl("http://192.168.1.10:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }
}
