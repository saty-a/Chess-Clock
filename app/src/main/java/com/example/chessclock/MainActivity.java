package com.example.chessclock;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    LinearLayout tp,btm,ll;
    TextView tptimer,btmtimer;
    ImageView Reset,Pause,Settings;
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
        initUi();

        tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startTimerBottomclock();
                showReset();

                if (mTimerRunning) {
                    Vibrate();
                    pauseTimer();
                }
            }
        });

        btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startTimerTopClock();
                showReset();

                if (mBTimerRunning){
                    Vibrate();
                    pauseTimerBottom();
                }
            }
        });

        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,SettingActivity.class);
                startActivity(i);
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
        updateCountDownText();
    }

    private void showReset() {
        if (mTimerRunning || mBTimerRunning){
            Reset.setVisibility(View.VISIBLE);
        }
    }

    private void initUi() {
        ll=findViewById(R.id.ll);
        tp=findViewById(R.id.top);
        btm=findViewById(R.id.bottom);
        tptimer=findViewById(R.id.uptimer);
        btmtimer=findViewById(R.id.btmtimer);
        Reset=findViewById(R.id.reset);
        Pause=findViewById(R.id.pause);
        Settings=findViewById(R.id.settings);
    }

    private void Vibrate() {
        // get the VIBRATOR_SERVICE system service
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        final VibrationEffect vibrationEffect2;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {

            // create vibrator effect with the constant EFFECT_CLICK
            vibrationEffect2 = VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK);

            // it is safe to cancel other vibrations currently taking place
            vibrator.cancel();

            vibrator.vibrate(vibrationEffect2);
        }
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
        Pause.setVisibility(View.VISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimerTop.cancel();
        mTimerRunning = false;
    }

    private void resetTimer() {
        if(mTimerRunning || mBTimerRunning){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            // Set the message show for the Alert time
            builder.setMessage("Do you want to reset ?");
            // Set Alert Title
            builder.setTitle("Alert !");
            // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
            builder.setCancelable(false);
            // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                // When the user click yes button then app will close
                mTimeLeftInMillisTop = START_TIME_IN_MILLIS;
                updateCountDownText();
                Reset.setVisibility(View.INVISIBLE);
            });

            // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                // If user click no then dialog box is canceled.
                dialog.cancel();
            });

            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();
        }
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillisTop / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillisTop / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tptimer.setText(timeLeftFormatted);
    }


    private void pauseBothclock(){
        if(mTimerRunning){
            mCountDownTimerTop.cancel();
            mTimerRunning = false;
            Pause.setVisibility(View.INVISIBLE);
        }
        if(mBTimerRunning){
            mCountDownTimerBottom.cancel();
            mBTimerRunning = false;
            Pause.setVisibility(View.INVISIBLE);
        }

    }
}