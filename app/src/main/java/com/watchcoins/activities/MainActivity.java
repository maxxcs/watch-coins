package com.watchcoins.activities;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.watchcoins.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.FrameLayout;

import com.watchcoins.fragments.CurrenciesFragment;
import com.watchcoins.fragments.MarketsFragment;
import com.watchcoins.fragments.FavoritesFragment;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView navView;
    private FrameLayout frame;
    private boolean preventReplace;
    private static final String FRAGMENT_CURRENCIES = "FRAGMENT_CURRENCIES";
    private static final String FRAGMENT_MARKETS = "FRAGMENT_MARKETS";
    private static final String FRAGMENT_FAVORITES = "FRAGMENT_FAVORITES";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_currencies:
                    CurrenciesFragment currencies = new CurrenciesFragment();
                    fragmentController(currencies, FRAGMENT_CURRENCIES);
                    return true;
                case R.id.navigation_marketplaces:
                    MarketsFragment markets = new MarketsFragment();
                    fragmentController(markets, FRAGMENT_MARKETS);
                    return true;
                case R.id.navigation_favorites:
                    FavoritesFragment favorites = new FavoritesFragment();
                    fragmentController(favorites, FRAGMENT_FAVORITES);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame = findViewById(R.id.fragment_content);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        CurrenciesFragment currencies = new CurrenciesFragment();
        fragmentController(currencies, FRAGMENT_CURRENCIES);
    }


    private void fragmentController(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, fragment, tag);
        fragmentTransaction.commit();
    }

}
