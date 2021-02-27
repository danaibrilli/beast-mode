package com.alilepidrasi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Workouts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        Button abs = findViewById(R.id.btnAbs);
        Button squats = findViewById(R.id.btnSquats);
        Button pullUps = findViewById(R.id.btnPullUps);
        Button pushUps = findViewById(R.id.btnPushUps);

        abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Abs.class);
                startActivity(intent);
            }
        });

        squats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Squats.class);
                startActivity(intent);
            }
        });

        pullUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PullUps.class);
                startActivity(intent);
            }
        });

        pushUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PushUps.class);
                startActivity(intent);
            }
        });
    }
}