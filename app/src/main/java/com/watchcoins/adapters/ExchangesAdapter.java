package com.watchcoins.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.watchcoins.activities.ExchangeDetailsActivity;
import com.watchcoins.models.Exchange;

import java.util.List;

import com.watchcoins.R;

public class ExchangesAdapter extends RecyclerView.Adapter<ExchangesAdapter.ViewHolderExchange> {

    private Context context;
    private List<Exchange> exchanges;
    private Intent details;

    public ExchangesAdapter(List<Exchange> exchanges) { this.exchanges = exchanges; }

    @NonNull
    @Override
    public ExchangesAdapter.ViewHolderExchange onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        details = new Intent(context, ExchangeDetailsActivity.class);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.instance_exchange, parent, false);
        ViewHolderExchange holder = new ViewHolderExchange(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ExchangesAdapter.ViewHolderExchange holder, final int position) {
        holder.exchangeName.setText(exchanges.get(position).getName());
        if (exchanges.get(position).getVolumeUsd() != null) {
            holder.exchangeVolume.setText(String.format("U$%1$,.2f", Double.valueOf(exchanges.get(position).getVolumeUsd())));
        } else {
            holder.exchangeVolume.setText("N/A");
        }
        if (exchanges.get(position).getPercentTotalVolume() != null) {
            holder.exchangePer.setText(String.format("%.2f%%", Double.valueOf(exchanges.get(position).getPercentTotalVolume())));
        } else {
            holder.exchangePer.setText("N/A");
        }

        holder.exchangeContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                details.putExtra("EXCHANGE", (new Gson().toJson(exchanges.get(position))));
                context.startActivity(details);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exchanges.size();
    }

    public class ViewHolderExchange extends RecyclerView.ViewHolder {
        public LinearLayout exchangeContainer;
        public TextView exchangeName;
        public TextView exchangeVolume;
        public TextView exchangePer;

        public ViewHolderExchange(@NonNull View itemView) {
            super(itemView);
            exchangeContainer = itemView.findViewById(R.id.exchange_container);
            exchangeName = itemView.findViewById(R.id.exchange_name);
            exchangeVolume = itemView.findViewById(R.id.exchange_volume);
            exchangePer = itemView.findViewById(R.id.exchange_per);
        }
    }
}
