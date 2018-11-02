package com.bignerdranch.android.fishfarm;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

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
        pools.add("Басейн №1");
        pools.add("Басейн №2");

        mButtonTemperatureMeasuring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setIndeterminate(true);

                final Handler h = new Handler() {
                    @Override
                    public void handleMessage(Message message) {
                        mTextViewDegreeTemperature.setText(measureTemperature());

                        mProgressBar.setVisibility(View.INVISIBLE);
                    }
                };
                h.sendMessageDelayed(new Message(), 1000);


            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pools);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerChoosePool.setAdapter(adapter);


    }

    public String measureTemperature() {
        return "15 C";
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TemperatureActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
