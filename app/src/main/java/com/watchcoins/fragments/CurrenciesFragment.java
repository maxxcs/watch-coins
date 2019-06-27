package com.watchcoins.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.watchcoins.R;
import com.watchcoins.adapters.CurrenciesAdapter;
import com.watchcoins.models.CurrenciesModel;
import com.watchcoins.services.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrenciesFragment extends Fragment {

    private CurrenciesModel data;
    private RecyclerView list;
    private CurrenciesAdapter adapter;

    public CurrenciesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currencies, container, false);
        list = view.findViewById(R.id.currencies_list);
        fetchCurrenciesData();
        return view;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void fetchCurrenciesData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<CurrenciesModel> call = api.getCurrencies();

        call.enqueue(new Callback<CurrenciesModel>() {
            @Override
            public void onResponse(Call<CurrenciesModel> call, Response<CurrenciesModel> response) {
                data = response.body();
                Log.i("TIMESTAMP", String.valueOf(data.getTimestamp()));
                 setupRecycleView();
            }

            @Override
            public void onFailure(Call<CurrenciesModel> call, Throwable t) {
                Toast.makeText(getContext(), "Fetch fail", Toast.LENGTH_SHORT).show();
                Log.i("Fetch fail", t.getMessage());
            }
        });
    }

    public void setupRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        list.setLayoutManager(layoutManager);
        list.setHasFixedSize(true);
        Log.i("BTC VALUE", String.valueOf(data.getData().get(0).getPriceUsd()));
        adapter = new CurrenciesAdapter(data.getData());
        list.setAdapter(adapter);
    }
}
