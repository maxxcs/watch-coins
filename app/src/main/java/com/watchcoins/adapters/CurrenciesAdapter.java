package com.watchcoins.adapters;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
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
    public void onBindViewHolder(@NonNull final CurrenciesAdapter.ViewHolderCurrency holder, int position) {
        holder.coinName.setText(currencies.get(position).getName());
        holder.coinValue.setText(String.format("U$%1$,.2f", Double.valueOf(currencies.get(position).getPriceUsd())));
        holder.coinChange.setText(String.format("%.2f%%", Double.valueOf(currencies.get(position).getChangePercent24Hr())));

        Picasso.get()
                .load("https://static.coincap.io/assets/icons/" + currencies.get(position).getSymbol().toLowerCase() + "@2x.png")
                .resize(100, 100)
                .centerCrop()
                .into(holder.coinImg, new Callback() {
                    @Override
                    public void onSuccess() { }
                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load("https://coincap.io/static/logo_mark.png")
                                .resize(100, 100)
                                .centerCrop()
                                .into(holder.coinImg);
                    }
                });

        holder.coinContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Click", "Click");
            }
        });
        //holder.coinContainer.setBackgroundColor();
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public class ViewHolderCurrency extends RecyclerView.ViewHolder {
        public LinearLayout coinContainer;
        public ImageView coinImg;
        public TextView coinName;
        public TextView coinValue;
        public TextView coinChange;

        public ViewHolderCurrency(@NonNull View itemView) {
            super(itemView);
            coinContainer = itemView.findViewById(R.id.coin_ref_container);
            coinImg = itemView.findViewById(R.id.coin_img);
            coinName = itemView.findViewById(R.id.coin_name);
            coinValue = itemView.findViewById(R.id.coin_value);
            coinChange = itemView.findViewById(R.id.coin_change);
        }
    }
}
