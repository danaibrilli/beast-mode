package com.alilepidrasi.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class PushUps extends AppCompatActivity {
    int reps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_push_ups);

        TextView reps_text = findViewById(R.id.tvReps);
        reps_text.setText("Getting your data ...");

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        String username = "DummyUser";

        mDatabase.child("Users").child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String response;
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    response = String.valueOf(task.getResult().getValue());
                    try {
                        JSONObject obj = new JSONObject(response);
                        String reps_s = obj.getString("reps_push_ups");
                        reps = Integer.parseInt(reps_s);
                        do_this();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });









    }

    public void do_this(){
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