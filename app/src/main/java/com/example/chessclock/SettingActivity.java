package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Objects;

public class SettingActivity extends AppCompatActivity {
    RadioButton blitz,bullet,rapid,fischerfive;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initUI();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (blitz.isChecked()){
                    Toast.makeText(SettingActivity.this, "blitz is selected", Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(SettingActivity.this, MainActivity.class);
                    i.putExtra("value",300000);
                    startActivity(i);
                }else if (bullet.isChecked()){
                    Toast.makeText(SettingActivity.this, "Bullet is selected", Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(SettingActivity.this, MainActivity.class);
                    i.putExtra("value",60000);
                    startActivity(i);
                }else if (rapid.isChecked()){
                    Toast.makeText(SettingActivity.this, "Rapid is selected", Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(SettingActivity.this, MainActivity.class);
                    i.putExtra("value",600000);
                    startActivity(i);
                }else if (fischerfive.isChecked()){
                    Intent i= new Intent(SettingActivity.this, MainActivity.class);
                    i.putExtra("value",300000);
                    startActivity(i);
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