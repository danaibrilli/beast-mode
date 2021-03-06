package com.alilepidrasi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class Workouts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_workouts);

        Button abs = findViewById(R.id.btnAbs);
        Button squats = findViewById(R.id.btnSquats);
        Button pullUps = findViewById(R.id.btnPullUps);
        Button pushUps = findViewById(R.id.btnPushUps);

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;


        abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "Hey! Try working on your push-ups, since abs page is under construction!";

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });


        squats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "Hey! Try working on your push-ups, since squats page is under construction!";

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        pullUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "Hey! Try working on your push-ups, since pull-ups page is under construction!";

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
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