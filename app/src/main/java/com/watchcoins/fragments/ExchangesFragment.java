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
import com.watchcoins.adapters.ExchangesAdapter;
import com.watchcoins.models.Exchange;
import com.watchcoins.models.ExchangesModel;
import com.watchcoins.services.Api;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExchangesFragment extends Fragment {

    private List<Exchange> data;
    private RecyclerView list;
    private ExchangesAdapter adapter;
    private Timer refresh;

    public ExchangesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchanges, container, false);
        list = view.findViewById(R.id.exchanges_list);
        refresh = new Timer();
        data = new ArrayList<>();

        refresh.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                fetchExchangeData();
            }
        },0,10000);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        refresh.cancel();
    }

    public void fetchExchangeData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<ExchangesModel> call = api.getExchanges();

        call.enqueue(new Callback<ExchangesModel>() {
            @Override
            public void onResponse(Call<ExchangesModel> call, Response<ExchangesModel> response) {
                if (response.body() != null) {
                    data.clear();
                    data.addAll(response.body().getData());
                    setupRecycleView();
                }
            }

            @Override
            public void onFailure(Call<ExchangesModel> call, Throwable t) {
                Toast.makeText(getContext(), "Fetch fail", Toast.LENGTH_SHORT).show();
                Log.i("Fetch fail", t.getMessage());
            }
        });
    }

    public void setupRecycleView() {
        if (adapter == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            list.setLayoutManager(layoutManager);
            list.setHasFixedSize(true);
            adapter = new ExchangesAdapter(data);
            list.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

}
