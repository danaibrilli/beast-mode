package com.alilepidrasi.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PushUps extends AppCompatActivity {
    int reps = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_push_ups);


        TextView reps_text = findViewById(R.id.tvReps);

        reps_text.setText("" + reps + "\n reps remaining");

        reps_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reps > 0) {
                    reps--;
                    reps_text.setText("" + reps + "\n reps remaining");
                } else {
                    reps_text.setText("Finished!");
                }
            }
        });


        ConstraintLayout lay = (ConstraintLayout) findViewById(R.id.pushupspage);
        lay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (reps > 0) {
                    reps--;
                    reps_text.setText("" + reps + "\n reps remaining");
                } else {
                    reps_text.setText("Finished!");
                }
            }
        });

        ImageButton pushUpsInfo = (ImageButton) PushUps.this.findViewById(R.id.btninfoworkouts);
        pushUpsInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PushUpsInfo.class);
                startActivity(intent);
            }
        });

    }
}