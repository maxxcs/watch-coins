package com.watchcoins.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.watchcoins.R;
import com.watchcoins.models.Exchange;
import com.watchcoins.models.ExchangeDetailsModel;
import com.watchcoins.services.Api;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExchangeDetailsActivity extends AppCompatActivity {

    private Timer refresh;
    private Exchange exchange;
    private TextView name;
    private TextView rank;
    private TextView perVolume;
    private TextView volume;
    private TextView pairs;
    private Button urlBtn;
    private Intent browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_details);

        exchange = new Gson().fromJson(getIntent().getStringExtra("EXCHANGE"), Exchange.class);
        refresh = new Timer();

        name = findViewById(R.id.ed_name);
        rank = findViewById(R.id.ed_rank);
        perVolume = findViewById(R.id.ed_per_total_volume);
        volume = findViewById(R.id.ed_volume);
        pairs = findViewById(R.id.ed_pairs);
        urlBtn = findViewById(R.id.ed_url_btn);

        render(exchange);

        urlBtn.setText("Go to page");
        browser = new Intent(Intent.ACTION_VIEW, Uri.parse(exchange.getExchangeUrl()));

        refresh.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                fetchData();
            }
        },0,6000);

        urlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(browser);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        refresh.cancel();
    }

    public void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<ExchangeDetailsModel> call = api.getExchangeDetails(exchange.getExchangeId());

        call.enqueue(new Callback<ExchangeDetailsModel>() {
            @Override
            public void onResponse(Call<ExchangeDetailsModel> call, Response<ExchangeDetailsModel> response) {
                if (response.body() != null) {
                    ExchangeDetailsModel ex;
                    ex = response.body();
                    render(ex.getData());
                }
            }

            @Override
            public void onFailure(Call<ExchangeDetailsModel> call, Throwable t) {
                Toast.makeText(ExchangeDetailsActivity.this, "Fetch fail", Toast.LENGTH_SHORT).show();
                Log.i("Fetch fail", t.getMessage());
            }
        });
    }

    public void render(Exchange ex) {
        Log.i("VALUE", ex.getVolumeUsd());

        name.setText(ex.getName());
        rank.setText(ex.getRank());
        if (ex.getPercentTotalVolume() != null) {
            perVolume.setText(String.format("%.2f%%", Double.valueOf(ex.getPercentTotalVolume())));
        }
        if (ex.getVolumeUsd() != null) {
            volume.setText(String.format("U$%1$,.0f", Double.valueOf(ex.getVolumeUsd())));
        }
        pairs.setText(ex.getTradingPairs());
    }

}
