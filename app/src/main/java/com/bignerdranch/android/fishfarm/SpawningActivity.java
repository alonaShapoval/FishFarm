package com.bignerdranch.android.fishfarm;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpawningActivity extends AppCompatActivity {
    @BindView(R.id.date_temperature)
    TextView mTextViewDataTemperature;
    @BindView(R.id.date_water_condition)
    TextView mTextViewWaterCondition;
    @BindView(R.id.date_fish_age)
    TextView mTextViewFishAge;
    @BindView(R.id.possible_spawning)
    TextView mTextViewSpawning;

    int averageTemperature;
    int averageWaterCondition;
    int fishAge;
    @BindView(R.id.choose_pool)
    Spinner mSpinnerChoosePool;
    ArrayList<String> pools = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spawning);
        ButterKnife.bind(this);
        pools.add("Басейн №1");
        pools.add("Басейн №2");
        mTextViewDataTemperature.setText(getAverageTemperature() + " C");
        mTextViewWaterCondition.setText(getAverageWaterCondition() + " %");
        mTextViewFishAge.setText(getFishAge() + " years");
        if (isPossibleSpawning()) {
            mTextViewSpawning.setText(R.string.can);
        } else {
            mTextViewSpawning.setText(R.string.cannot);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pools);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerChoosePool.setAdapter(adapter);
    }

    public int getAverageTemperature() {
        return 13;
    }

    public int getAverageWaterCondition() {
        return 80;
    }

    public int getFishAge() {
        return 2;
    }

    public boolean isPossibleSpawning() {
        if (fishAge >= Constants.MIN_FISH_AGE && averageTemperature >= Constants.MIN_POSSIBLE_TEMPERATURE
                && averageTemperature <= Constants.MAX_POSSIBLE_TEMPERATURE &&
                averageWaterCondition <= Constants.MAX_POSSIBLE_WATER_CONDITION
                ) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SpawningActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
