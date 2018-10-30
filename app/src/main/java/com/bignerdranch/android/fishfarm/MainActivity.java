package com.bignerdranch.android.fishfarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.content.*;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mLinearLayoutTemperatureMeasuring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,TemperatureActivity.class);
                startActivity(intent);
            }
        });

        mLinearLayoutWaterMeasuring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,WaterConditionActivity.class);
                startActivity(intent);
            }
        });

        mLinearLayoutFeeding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mLinearLayoutSpawning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
