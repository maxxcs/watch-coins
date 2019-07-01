package com.watchcoins.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.watchcoins.R;
import com.watchcoins.models.Bookmark;

import io.realm.Realm;
import io.realm.RealmResults;

public class FavoritesFragment extends Fragment {

    private Realm realm;

    public FavoritesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        realm = Realm.getDefaultInstance();

        final RealmResults<Bookmark> favorites = realm.where(Bookmark.class).findAll();
        Log.i("REALM", String.valueOf(favorites.size()));

        return view;
    }

}
