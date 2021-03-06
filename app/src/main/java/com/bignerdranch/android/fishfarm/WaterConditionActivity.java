package com.bignerdranch.android.fishfarm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.Request;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WaterConditionActivity extends AppCompatActivity {
    static final int DATE_DIALOG_ID = 999;
    @BindView(R.id.measure_oxygen)
    Button mButtonMeasureOxygen;
    @BindView(R.id.date)
    TextView mTextViewDate;
    @BindView(R.id.change_date)
    Button mButtonChangeDate;
    @BindView(R.id.oxygen_data)
    TextView mTextViewOxygenData;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private int mYear, mMonth, mDay, mHour, mMinute;

    @BindView(R.id.choose_pool)
    Spinner mSpinnerChoosePool;
    ArrayList<String> pools = new ArrayList<>();
    final Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_condition);
        ButterKnife.bind(this);
        pools.add("Pools");
        getPools();

        setInitialDate();
        mButtonChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDate();
            }
        });
        mButtonMeasureOxygen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                measureOxygen();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pools);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerChoosePool.setAdapter(adapter);
    }


    public void setInitialDate() {
        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mTextViewDate.setText(mDay + "-" + (mMonth + 1) + "-" + mYear);
    }

    public void chooseDate() {
        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        mTextViewDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    public void measureOxygen() {
        WaterCondition t = new WaterCondition(this, Request.Method.GET);

        t.getOxygen(new WaterCondition.FishFarmServiceCallback() {
            @Override
            public void onResult(String answer) {
                if (!answer.equals("Error")) {
                    mTextViewOxygenData.setText(formatValue(answer));
                } else {
                    mTextViewOxygenData.setText("30%");
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
                    mTextViewOxygenData.setText("Error");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(WaterConditionActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private String formatValue(String val) {

        return "Current value of oxygen: " + val + " %";
    }
}
