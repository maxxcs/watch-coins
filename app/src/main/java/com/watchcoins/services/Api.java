package com.watchcoins.services;

import com.watchcoins.models.CurrenciesModel;
import com.watchcoins.models.MarketsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://api.coincap.io/v2";

    @GET("/assets")
    Call<List<CurrenciesModel>> getCurrencies();

    @GET("/markets")
    Call<List<MarketsModel>> getMarkets();
}