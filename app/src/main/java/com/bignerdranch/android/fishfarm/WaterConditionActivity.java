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
        pools.add("Басейн №1");
        pools.add("Басейн №2");

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
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setIndeterminate(true);
                final Handler h = new Handler() {
                    @Override
                    public void handleMessage(Message message) {
                        mTextViewOxygenData.setText(measureOxygen());

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

    public String measureOxygen() {
        return "30%";
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(WaterConditionActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
