package com.upm.pasproject.rest;

import com.upm.pasproject.rest.models.Model;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoinGeckoEndPoints {

    @GET("coins/markets?order=market_cap_desc&per_page=100&page=1&sparkline=false")
    Observable<List<Model>> listCoins(@Query("vs_currency") String currency,
                                      @Query("order") String order,
                                      @Query("per_page") int coins_per_page,
                                      @Query("page") int pages,
                                      @Query("sparkline") String sparkline);
}
