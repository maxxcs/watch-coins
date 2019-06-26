package com.watchcoins.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.watchcoins.models.CurrencyModel;

import java.util.List;

import com.watchcoins.R;

public class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesAdapter.ViewHolderCurrency> {

    List<CurrencyModel> currencies;

    public CurrenciesAdapter(List<CurrencyModel> currencies) {
        this.currencies = currencies;
    }

    @NonNull
    @Override
    public CurrenciesAdapter.ViewHolderCurrency onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.currency_instance, parent, false);
        ViewHolderCurrency holder = new ViewHolderCurrency(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrenciesAdapter.ViewHolderCurrency holder, int position) {
        holder.currencyName.setText(currencies.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public class ViewHolderCurrency extends RecyclerView.ViewHolder {

        public TextView currencyName;

        public ViewHolderCurrency(@NonNull View itemView) {
            super(itemView);
            currencyName = itemView.findViewById(R.id.currency_name);
        }
    }
}
