package com.watchcoins.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.watchcoins.R;

public class SplashActivity extends AppCompatActivity {

    private Intent currencies;
    private ImageView logoImg;
    private TextView logo;
    private TextView watch;
    private TextView coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        currencies = new Intent(this, CurrenciesActivity.class);
        logoImg = findViewById(R.id.logo_img);
        logo = findViewById(R.id.logo);
        watch = findViewById(R.id.watch);
        coins = findViewById(R.id.coins);
        Picasso.get().load("").resize(512, 512).centerCrop().into(logoImg, new Callback() {
            @Override
            public void onSuccess() {
                animate();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(SplashActivity.this, "Eita preula", Toast.LENGTH_SHORT).show();
                startActivity(currencies);
                finish();
            }
        });

    }

    public void animate() {
        final Animation animLogo = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_in);
        final Animation animWatch = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_in);
        final Animation animCoins = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_in);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logo.setVisibility(View.VISIBLE);
                logo.setAnimation(animLogo);
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                watch.setVisibility(View.VISIBLE);
                watch.setAnimation(animWatch);
            }
        }, 1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                coins.setVisibility(View.VISIBLE);
                coins.setAnimation(animCoins);
            }
        }, 2000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(currencies);
                finish();
            }
        }, 4500);

    }

}