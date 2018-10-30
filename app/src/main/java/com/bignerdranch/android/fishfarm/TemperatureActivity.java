package com.bignerdranch.android.fishfarm;

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
    final Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        ButterKnife.bind(this);
       final TimerTask task = new TimerTask() {
            public void run() {
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        };


        final long delay = 1000L;

        mButtonTemperatureMeasuring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setIndeterminate(true);
                timer.schedule(task, delay);
                mTextViewDegreeTemperature.setText("15 C");
            }
        });

    }
}
