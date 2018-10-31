package com.bignerdranch.android.fishfarm;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import butterknife.BindView;
import butterknife.ButterKnife;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        ButterKnife.bind(this);

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

    }

    public String measureTemperature() {
        return "15 C";
    }

}
