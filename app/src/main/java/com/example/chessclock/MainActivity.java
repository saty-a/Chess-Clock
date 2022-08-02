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



    private CountDownTimer mCountDownTimer,mCountDownTimerBottom;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

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
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimerTopClock();
                }
            }
        });

        btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btmtimer.setText("00:01");
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
                pauseTimer();
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
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                Pause.setVisibility(View.INVISIBLE);
                Reset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        Reset.setVisibility(View.INVISIBLE);
    }

    private void startTimerTopClock() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                Pause.setVisibility(View.INVISIBLE);
                Reset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        Reset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        Pause.setVisibility(View.GONE);
        Play.setVisibility(View.VISIBLE);
        Reset.setVisibility(View.VISIBLE);
    }

    private void playTimer() {
        startTimerTopClock();
        Play.setVisibility(View.GONE);
        Pause.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        Reset.setVisibility(View.INVISIBLE);
        Pause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        tptimer.setText(timeLeftFormatted);
    }
}