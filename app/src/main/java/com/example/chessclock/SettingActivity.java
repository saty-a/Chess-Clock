package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    RadioButton blitz,bullet,rapid,fischerfive;
    Button submit;
    TimerModel tm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initUI();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (blitz.isChecked()){
                    Toast.makeText(SettingActivity.this, "blitz is selected", Toast.LENGTH_SHORT).show();
                    tm=new TimerModel(0,5000);
                }else if (bullet.isChecked()){
                    Toast.makeText(SettingActivity.this, "Bullet is selected", Toast.LENGTH_SHORT).show();
                    tm=new TimerModel(1,1000);
                }else if (rapid.isChecked()){
                    Toast.makeText(SettingActivity.this, "Rapid is selected", Toast.LENGTH_SHORT).show();
                    tm=new TimerModel(2,10000);
                }else if (fischerfive.isChecked()){
                    Toast.makeText(SettingActivity.this, "Fischerfive is selected", Toast.LENGTH_SHORT).show();
                    tm=new TimerModel(3,5000);
                }else {
                    Toast.makeText(SettingActivity.this, "Select any of the options", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initUI() {
        blitz=findViewById(R.id.fiveRd);
        bullet=findViewById(R.id.oneMinRd);
        rapid=findViewById(R.id.fischerFive);
        fischerfive=findViewById(R.id.tenRd);
        submit=findViewById(R.id.confirmBtn);
    }
}