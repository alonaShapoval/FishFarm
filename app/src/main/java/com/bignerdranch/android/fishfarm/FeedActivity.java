package com.bignerdranch.android.fishfarm;

import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    @BindView(R.id.show_that_fed)
    TextView mTextViewShowFed;


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

                    }
                };
                t.start();



             //showAlertDialog(view);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        while(progressStatus < 100){
//                            // Update the progress status
//                            progressStatus +=1;
//
//                            // Try to sleep the thread for 20 milliseconds
//                            try{
//                                Thread.sleep(20);
//                            }catch(InterruptedException e){
//                                e.printStackTrace();
//                            }
//
//                            // Update the progress bar
//                            handler.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    mProgreesBar.setProgress(progressStatus);
//                                    // Show the progress on TextView
//                                    mTextViewFeedDate.setText(setCurrDate());
//
//                                }
//                            });
//                        }
//                        showAlertDialog(view);
//                    }
//                }).start(); // Start the operation
//
//               // showAlertDialog(view);
//            }

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

    public void showAlertDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        builder.setMessage(R.string.fed);
        builder.setCancelable(true);

        final AlertDialog dlg = builder.create();

        dlg.show();

        final Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                dlg.dismiss(); // when the task active then close the dialog
                timer.cancel(); // also just top the timer thread, otherwise,
                // you may receive a crash report
            }
        }, 3000); // через 5 секунд (5000 миллисекунд), the task will be active.
    }


}
