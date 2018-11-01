package com.bignerdranch.android.fishfarm;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedActivity extends AppCompatActivity {
@BindView(R.id.feed_date)
    TextView mTextViewFeedDate;
@BindView(R.id.progress_bar)
    ProgressBar mProgreesBar;
@BindView(R.id.feed)
Button mButtonFeed;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    static String lastDateFeed="31-10-2018\n22:17:44";
//    @BindView(R.id.show_that_fed)
//    TextView mTextViewShowFed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);
        mTextViewFeedDate.setText(lastDateFeed);
        mButtonFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                progressStatus = 0;
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        while (progressStatus < 100) {
                            // Update the progress status
                            progressStatus += 1;

//                             Try to sleep the thread for 20 milliseconds
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();

                            }

                            // Update the progress bar
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mProgreesBar.setProgress(progressStatus);
                                    // Show the progress on TextView
                                    mTextViewFeedDate.setText(setCurrDate());


                                }
                            });

                        }
                       // mTextViewShowFed.setText(R.string.fed);
                        Looper.prepare();
                        Toast.makeText(FeedActivity.this, R.string.fed,Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                };
                t.start();
            }
       });

    }
    public void feedFish(){

    }
    public String setCurrDate(){
        final Calendar calendar = Calendar.getInstance();
        StringBuilder date=new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        date.append(calendar.get(Calendar.DAY_OF_MONTH));
        date.append("-");
        date.append(calendar.get(Calendar.MONTH)+1);
        date.append("-");
        date.append(calendar.get(Calendar.YEAR));
        date.append("\n");
        date.append(sdf.format(calendar.getTime()));
        lastDateFeed=date.toString();
       return date.toString();
    }


}
