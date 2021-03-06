package com.alilepidrasi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Target extends AppCompatActivity {
    int weight = 82;
    double height = 1.8;
    int target_weight = 75;
    int LAUNCH_SECOND_ACTIVITY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_target);

        TextView currWeight = findViewById(R.id.currWeight);
        currWeight.setText("Current Weight: " + weight);

        TextView currTargetWeight = findViewById(R.id.currTarget);
        currTargetWeight.setText("Target Weight: " + target_weight);

        TextView currBMI = findViewById(R.id.currBMI);
        double bmi = weight / (height * height);
        double roundOff = (double) Math.round(bmi*100)/100;
        currBMI.setText("Current BMI: " + roundOff );

        TextView targetBMI = findViewById(R.id.targetBMI);
        double target_bmi = target_weight / (height * height);
        double target_roundOff = (double) Math.round(target_bmi*100)/100;
        targetBMI.setText("Target BMI: " + target_roundOff);

        Button newgoal = findViewById(R.id.btnSetGoal);
        newgoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NewGoal.class);
                startActivityForResult(i, LAUNCH_SECOND_ACTIVITY);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int new_weight = 0;
        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("new_weight");
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                try
                {
                    new_weight= Integer.parseInt(result);

                }
                catch (NumberFormatException e)
                {
                    new_weight=0;
                    CharSequence text1 = "Error occurred, please try again making sure you enter an integer";
                    Toast toast1 = Toast.makeText(context, text1, duration);
                    toast1.show();
                }

                if (new_weight == 0) {
                    CharSequence text2 = "No change in target weight";
                    Toast toast2 = Toast.makeText(context, text2, duration);
                    toast2.show();
                }
                else{
                    TextView currTargetWeight = findViewById(R.id.currTarget);
                    currTargetWeight.setText("Target Weight: " + new_weight);

                    TextView targetBMI = findViewById(R.id.targetBMI);
                    double target_bmi = new_weight / (height * height);
                    double target_roundOff = (double) Math.round(target_bmi*100)/100;
                    targetBMI.setText("Target BMI: " + target_roundOff);

                }


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                CharSequence text = "Error occurred, please try again";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }//onActivityResult

}