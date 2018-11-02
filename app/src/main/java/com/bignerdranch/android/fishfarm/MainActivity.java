package com.bignerdranch.android.fishfarm;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.content.*;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.temperature_measuring)
    LinearLayout mLinearLayoutTemperatureMeasuring;
    @BindView(R.id.water_measuring)
    LinearLayout mLinearLayoutWaterMeasuring;
    @BindView(R.id.feeding)
    LinearLayout mLinearLayoutFeeding;
    @BindView(R.id.spawning)
    LinearLayout mLinearLayoutSpawning;
    static String language = "uk";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mLinearLayoutTemperatureMeasuring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TemperatureActivity.class);
                startActivity(intent);
            }
        });

        mLinearLayoutWaterMeasuring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WaterConditionActivity.class);
                startActivity(intent);
            }
        });

        mLinearLayoutFeeding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FeedActivity.class);
                startActivity(intent);
            }
        });

        mLinearLayoutSpawning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SpawningActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Операции для выбранного пункта меню
        switch (item.getItemId()) {
            case R.id.change_locale:
                changeLocale();
                return true;
            case R.id.log_out:
                LogOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void changeLocale() {
        Locale locale = null;
        if (language.equalsIgnoreCase("en")) {
            locale = new Locale("en");
        } else if (language.equalsIgnoreCase("uk")) {
            locale = new Locale("uk");
            language = "en";
        }
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, null);
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void LogOut() {
        Toast.makeText(MainActivity.this, "Log out", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", "0");
        startActivity(intent);
    }
}
