package com.alilepidrasi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Squats extends AppCompatActivity {
    int reps = 15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squats);


        TextView reps_text= findViewById(R.id.tvReps);

        reps_text.setText(""+reps+"\n reps remaining");

        reps_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reps>0) {
                    reps--;
                    reps_text.setText("" + reps + "\n reps remaining");
                }
                else{
                    reps_text.setText("Finished!");
                }
            }
        });
    }
}