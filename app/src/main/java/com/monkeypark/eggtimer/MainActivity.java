package com.monkeypark.eggtimer;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Button buttonSwitch;
    int mins;
    int sec;
    boolean isSeekBarEnabled;
    CountDownTimer countDownTimer;



    public void timerSwitch(View view)
    {
        if(isSeekBarEnabled)
        {
            timerSeekBar.setEnabled(false);
            isSeekBarEnabled = false;
            buttonSwitch.setText("Stop");
            countDownTimer =  new CountDownTimer(timerSeekBar.getProgress()*1000,1000)
            {
                public void onTick(long l)
                {
                    updateTimer((int)l/1000);
                }
                public void onFinish()
                {
                    timerSeekBar.setEnabled(true);
                    isSeekBarEnabled = true;
                    buttonSwitch.setText("Start");
                    updateTimer(timerSeekBar.getProgress());
                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                    mPlayer.start();
                }
            };
            countDownTimer.start();
        }else
        {
            timerSeekBar.setEnabled(true);
            isSeekBarEnabled = true;
            buttonSwitch.setText("Start");
            countDownTimer.cancel();
        }

    }

    public void updateTimer(int second)
    {
        mins = (int) second / 60;
        sec = second % 60;
        if(sec < 10)
        {
            timerTextView.setText(String.valueOf(mins) + ":0" + String.valueOf(sec));
        }else
        {
            timerTextView.setText(String.valueOf(mins) + ":" + String.valueOf(sec));
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        buttonSwitch = (Button) findViewById(R.id.btnController);
        isSeekBarEnabled = true;
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);


        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
