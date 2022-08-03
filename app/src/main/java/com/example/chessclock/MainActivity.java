package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    LinearLayout tp,btm;
    TextView tptimer,btmtimer;
    ImageView Reset,Pause,Play;
    private static final long START_TIME_IN_MILLIS = 600000;



    private CountDownTimer mCountDownTimerTop,mCountDownTimerBottom;

    private boolean mTimerRunning,mBTimerRunning,switchClock=true;

    private long mTimeLeftInMillisTop = START_TIME_IN_MILLIS;
    private long mTimeLeftInMillisTBottom = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializations
        tp=findViewById(R.id.top);
        btm=findViewById(R.id.bottom);
        tptimer=findViewById(R.id.uptimer);
        btmtimer=findViewById(R.id.btmtimer);
        Reset=findViewById(R.id.reset);
        Pause=findViewById(R.id.pause);
        Play=findViewById(R.id.play);

        tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startTimerBottomclock();
                if (mTimerRunning) {
                    pauseTimer();
                }
            }
        });

        btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startTimerTopClock();
                if (mBTimerRunning){
                    pauseTimerBottom();
                }
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseBothclock();
            }
        });

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTimer();
            }
        });

    }

    private void startTimerBottomclock() {
        mCountDownTimerBottom = new CountDownTimer(mTimeLeftInMillisTBottom, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillisTBottom = millisUntilFinished;
                updateCountDownBottomText();
            }

            @Override
            public void onFinish() {
                mBTimerRunning = false;
            }
        }.start();

       mBTimerRunning = true;
        Reset.setVisibility(View.INVISIBLE);
        Pause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownBottomText() {
        int minutes = (int) (mTimeLeftInMillisTBottom / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillisTBottom / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        btmtimer.setText(timeLeftFormatted);
    }

    private void pauseTimerBottom() {
        mCountDownTimerBottom.cancel();
        mBTimerRunning = false;
    }

    // Top CLock functions

    private void startTimerTopClock() {
        mCountDownTimerTop = new CountDownTimer(mTimeLeftInMillisTop, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillisTop = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
            }
        }.start();

        mTimerRunning = true;
        Reset.setVisibility(View.INVISIBLE);
        Pause.setVisibility(View.VISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimerTop.cancel();
        mTimerRunning = false;
    }

    private void playTimer() {
        Play.setVisibility(View.GONE);
        Pause.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillisTop = START_TIME_IN_MILLIS;
        updateCountDownText();
        Reset.setVisibility(View.INVISIBLE);
        Pause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillisTop / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillisTop / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        tptimer.setText(timeLeftFormatted);
    }


    private void pauseBothclock(){
        mCountDownTimerTop.cancel();
        mTimerRunning = false;
        mCountDownTimerBottom.cancel();
        mBTimerRunning = false;
        Pause.setVisibility(View.GONE);
        Play.setVisibility(View.VISIBLE);
    }
}