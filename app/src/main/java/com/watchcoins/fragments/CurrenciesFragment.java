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
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrenciesFragment extends Fragment {

    private CurrenciesModel data;
    private RecyclerView list;
    private CurrenciesAdapter adapter;
    private Timer refresh;

    public CurrenciesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currencies, container, false);
        list = view.findViewById(R.id.currencies_list);
        refresh = new Timer();
        fetchCurrenciesData(true);
//        refresh.scheduleAtFixedRate(new TimerTask(){
//            @Override
//            public void run(){
//                fetchCurrenciesData(false);
//            }
//        },2000,2000);
        return view;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void fetchCurrenciesData(final Boolean isInitialize) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<CurrenciesModel> call = api.getCurrencies();

        call.enqueue(new Callback<CurrenciesModel>() {
            @Override
            public void onResponse(Call<CurrenciesModel> call, Response<CurrenciesModel> response) {
                if (isInitialize) {
                    data = response.body();
                    setupRecycleView();
                } else {
                    data.setData(response.body().getData());
                    updateRecycleView();
                }
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
        adapter = new CurrenciesAdapter(data.getData());
        list.setAdapter(adapter);
    }

    public void updateRecycleView() {
        Log.i("BTC VALUE", data.getData().get(0).getPriceUsd());
        adapter.notifyDataSetChanged();
    }
}
