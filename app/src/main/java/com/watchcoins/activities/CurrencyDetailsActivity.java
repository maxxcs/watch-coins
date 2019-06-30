package com.watchcoins.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.watchcoins.R;
import com.watchcoins.models.CurrencyDetailsModel;
import com.watchcoins.services.Api;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyDetailsActivity extends AppCompatActivity {

    private String id;
    private Timer refresh;
    private CurrencyDetailsModel currency;
    private ImageView image;
    private TextView name;
    private TextView symbol;
    private TextView rank;
    private TextView price;
    private TextView change;
    private TextView marketCap;
    private TextView volume;
    private TextView supply;
    private TextView maxSupply;
    private Button favoriteBtn;
    private Boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_details);
        id = getIntent().getStringExtra("CURRENCY_ID");
        refresh = new Timer();

        image = findViewById(R.id.dt_img);
        name = findViewById(R.id.dt_name);
        symbol = findViewById(R.id.dt_symbol);
        rank = findViewById(R.id.dt_rank);
        price = findViewById(R.id.dt_price);
        change = findViewById(R.id.dt_change);
        marketCap = findViewById(R.id.dt_market_cap);
        volume = findViewById(R.id.dt_volume);
        supply = findViewById(R.id.dt_supply);
        maxSupply = findViewById(R.id.dt_max_supply);
        favoriteBtn = findViewById(R.id.dt_favorite_btn);

        isFavorite = false;
        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavorite) favoriteBtn.setText("Favoritar");
                else favoriteBtn.setText("Desfavoritar");
                isFavorite = !isFavorite;
            }
        });

        refresh.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                fetchData();
            }
        },0,6000);
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
        Call<CurrencyDetailsModel> call = api.getCurrencyDetails(id);

        call.enqueue(new Callback<CurrencyDetailsModel>() {
            @Override
            public void onResponse(Call<CurrencyDetailsModel> call, Response<CurrencyDetailsModel> response) {
                currency = response.body();
                render();
            }

            @Override
            public void onFailure(Call<CurrencyDetailsModel> call, Throwable t) {
                Toast.makeText(CurrencyDetailsActivity.this, "Fetch fail", Toast.LENGTH_SHORT).show();
                Log.i("Fetch fail", t.getMessage());
            }
        });
    }

    public void render() {
        Log.i("VALUE", currency.getData().getPriceUsd());
        name.setText(currency.getData().getName());
        symbol.setText(currency.getData().getSymbol());
        rank.setText(currency.getData().getRank());
        price.setText(String.format("U$%1$,.2f", Double.valueOf(currency.getData().getPriceUsd())));
        change.setText(String.format("%.2f%%", Double.valueOf(currency.getData().getChangePercent24Hr())));
        marketCap.setText(String.format("U$%1$,.0f", Double.valueOf(currency.getData().getMarketCapUsd())));
        volume.setText(String.format("U$%1$,.0f", Double.valueOf(currency.getData().getVolumeUsd24Hr())));
        supply.setText(String.format("%1$,.2f", Double.valueOf(currency.getData().getSupply())));
        if (currency.getData().getMaxSupply() != null)
            maxSupply.setText(String.format("%1$,.2f", Double.valueOf(currency.getData().getMaxSupply())));
        else
            maxSupply.setText("No limit");

        Picasso.get()
                .load("https://static.coincap.io/assets/icons/" + currency.getData().getSymbol().toLowerCase() + "@2x.png")
                .into(image, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() { }
                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                            .load("https://coincap.io/static/logo_mark.png")
                            .into(image);
                        }
                });
    }

}
