package com.watchcoins.services;

import com.watchcoins.models.CurrenciesModel;
import com.watchcoins.models.CurrencyDetailsModel;
import com.watchcoins.models.Exchange;
import com.watchcoins.models.ExchangeDetailsModel;
import com.watchcoins.models.ExchangesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "https://api.coincap.io/v2/";

    @GET("assets")
    Call<CurrenciesModel> getCurrencies();

    @GET("assets/{id}")
    Call<CurrencyDetailsModel> getCurrencyDetails(@Path("id") String id);

    @GET("exchanges")
    Call<ExchangesModel> getExchanges();

    @GET("exchanges/{id}")
    Call<ExchangeDetailsModel> getExchangeDetails(@Path("id") String id);

}