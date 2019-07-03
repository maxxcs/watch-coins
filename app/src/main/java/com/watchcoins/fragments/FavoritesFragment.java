package com.watchcoins.fragments;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.watchcoins.R;
import com.watchcoins.activities.CurrencyDetailsActivity;
import com.watchcoins.adapters.CurrenciesAdapter;
import com.watchcoins.models.Bookmark;
import com.watchcoins.models.CurrenciesModel;
import com.watchcoins.models.Currency;
import com.watchcoins.models.CurrencyDetailsModel;
import com.watchcoins.services.Api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoritesFragment extends Fragment {


    private Realm realm;
    private List<Bookmark> favorites;
    private List<Currency> data;
    private RecyclerView list;
    private CurrenciesAdapter adapter;
    private Timer refresh;

    public FavoritesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        list = view.findViewById(R.id.bookmark_list);
        realm = Realm.getDefaultInstance();
        refresh = new Timer();
        data = new ArrayList<>();

        refresh.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                if (favorites != null) {
                    for (int i = 0; i < favorites.size(); i++) {
                        fetchData(favorites.get(i).getId());
                    }
                }
            }
        },0,1500);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        favorites = realm.copyFromRealm(realm.where(Bookmark.class).findAll());
        data.clear();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        refresh.cancel();
    }

    public void fetchData(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<CurrencyDetailsModel> call = api.getCurrencyDetails(id);

        call.enqueue(new Callback<CurrencyDetailsModel>() {
            @Override
            public void onResponse(Call<CurrencyDetailsModel> call, Response<CurrencyDetailsModel> response) {
                if (response.body() != null) {
                    Currency currency = response.body().getData();
                    int position = data.size();
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getId().equals(currency.getId())) {
                            data.remove(i);
                            position = i;
                            break;
                        }
                    }
                    data.add(position, currency);
                    setupRecycleView();
                }
            }

            @Override
            public void onFailure(Call<CurrencyDetailsModel> call, Throwable t) {
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
            adapter = new CurrenciesAdapter(data);
            list.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    public void getRealmData() {

    }
}
