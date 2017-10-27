package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yasin_000 on 10.10.2017.
 */

public class VideoApi {
    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://www.googleapis.com/")
                    .build();
        }
        return retrofit;
    }
}