package com.bignerdranch.android.fishfarm;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.android.volley.Request;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class TemperatureActivity extends AppCompatActivity {
    @BindView(R.id.degree_temperature)
    TextView mTextViewDegreeTemperature;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.measure)
    Button mButtonTemperatureMeasuring;
    @BindView(R.id.choose_pool)
    Spinner mSpinnerChoosePool;
    ArrayList<String> pools = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        ButterKnife.bind(this);
        pools.add("Pools");
        getPools();

        mButtonTemperatureMeasuring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                measureTemperature();

            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pools);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerChoosePool.setAdapter(adapter);

    }

    public void measureTemperature() {
        Temperature t = new Temperature(this, Request.Method.GET);

        t.getAllTemperature(new Temperature.FishFarmServiceCallback() {
            @Override
            public void onResult(String answer) {
                if (!answer.equals("Error")) {
                    mTextViewDegreeTemperature.setText(formatValue(answer));
                } else {
                    mTextViewDegreeTemperature.setText("Error");
                }
            }
        });

    }

    public void getPools() {
        Pool t = new Pool(this, Request.Method.GET);

        t.getPools(new Pool.FishFarmServiceCallback() {
            @Override
            public void onResult(String answer) {
                if (!answer.equals("Error")) {
                    pools.add("Pools #" + answer);
                } else {
                    mTextViewDegreeTemperature.setText("Error");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TemperatureActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private String formatValue(String val) {

        return "Current temperature: " + val + " C";
    }

}
