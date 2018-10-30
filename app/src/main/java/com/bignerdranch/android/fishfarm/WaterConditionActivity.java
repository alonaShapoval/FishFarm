package com.bignerdranch.android.fishfarm;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WaterConditionActivity extends AppCompatActivity {
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

    Calendar dateAndTime=Calendar.getInstance();
    final Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_condition);
        ButterKnife.bind(this);
        final TimerTask task = new TimerTask() {
            public void run() {
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        };


        final long delay = 1000L;
        mButtonMeasureOxygen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextViewOxygenData.setText(measureOxygen());
            }
        });
        mButtonChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setInitialDate();
            }
        });
        mButtonMeasureOxygen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setIndeterminate(true);
                timer.schedule(task, delay);
            }
        });
    }
    public String measureOxygen(){
        return "30%";
    }
    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(WaterConditionActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }


    // установка начальных даты и времени
    private void setInitialDate() {

//        mTextViewDate.setText(DateUtils.formatDateTime(this,
//                dateAndTime.getTimeInMillis(),
//                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
//                        | DateUtils.FORMAT_SHOW_TIME));
        mTextViewDate.setText(dateAndTime.get(Calendar.DATE));
    }



    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDate();
        }
    };
}
