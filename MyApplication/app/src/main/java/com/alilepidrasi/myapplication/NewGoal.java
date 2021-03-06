package com.alilepidrasi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewGoal extends AppCompatActivity {

    int new_weight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_goal);

        double height = 1.8;

        EditText newgoal = findViewById(R.id.editNewGoal);

        newgoal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try
                {
                    new_weight= Integer.parseInt(newgoal.getText().toString());
                }
                catch (NumberFormatException e)
                {
                    new_weight=0;
                }

                double new_bmi = new_weight / (height * height);
                double new_round_bmi = (double) Math.round(new_bmi*100)/100;
                ((TextView)findViewById(R.id.tvBMI)).setText("Target BMI will be:\n"+new_round_bmi);
            }
        });


        Button setGoal = findViewById(R.id.btnNewGoal);
        setGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent returnIntent = new Intent();
                returnIntent.putExtra("new_weight",String.valueOf(new_weight));
                setResult(Activity.RESULT_OK,returnIntent);

                finish();
            }
        });


    }
}