package com.watchcoins.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

    private Intent main;
    private ImageView logo;
    private TextView watch;
    private TextView coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        main = new Intent(this, MainActivity.class);
        logo = findViewById(R.id.logo_img);
        watch = findViewById(R.id.watch);
        coins = findViewById(R.id.coins);
        Picasso.get()
            .load("https://raw.githubusercontent.com/maxxcs/watch-coins/master/utils/bitcoin.png")
            .resize(512, 512)
            .centerCrop()
            .into(logo, new Callback() {

                @Override
                public void onSuccess() {
                    animate();
                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(SplashActivity.this, "Image not found", Toast.LENGTH_SHORT).show();
                    startActivity(main);
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
        }, 200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                watch.setVisibility(View.VISIBLE);
                watch.setAnimation(animWatch);
            }
        }, 1200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                coins.setVisibility(View.VISIBLE);
                coins.setAnimation(animCoins);
            }
        }, 1700);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(main);
                finish();
            }
        }, 4500);

    }

}
