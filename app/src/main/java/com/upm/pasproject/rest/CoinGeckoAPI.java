package com.upm.pasproject.rest;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoinGeckoAPI {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    private static CoinGeckoEndPoints service;

    public static synchronized CoinGeckoEndPoints getInstance(){
        if(service == null){
            service = retrofit.create(CoinGeckoEndPoints.class);
        }
        return service;
    }
}
