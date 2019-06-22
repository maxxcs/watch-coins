package com.watchcoins.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.watchcoins.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrenciesFragment extends Fragment {


    public CurrenciesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currencies, container, false);
    }

}
