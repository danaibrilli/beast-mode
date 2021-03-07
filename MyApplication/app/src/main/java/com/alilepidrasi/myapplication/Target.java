package com.alilepidrasi.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class Target extends AppCompatActivity {
    int weight = 82;
    int height = 180;
    int target_weight = 75;
    int LAUNCH_SECOND_ACTIVITY = 1;
    String username = "DummyUser";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_target);

        CharSequence text = "Getting your data from our database,please wait";
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();

        TextView currWeight = findViewById(R.id.currWeight);
        currWeight.setText("Current Weight: ");

        TextView currTargetWeight = findViewById(R.id.currTarget);
        currTargetWeight.setText("Target Weight: ");

        TextView currBMI = findViewById(R.id.currBMI);
        currBMI.setText("Current BMI: ");

        TextView targetBMI = findViewById(R.id.targetBMI);
        targetBMI.setText("Target BMI: ");

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
                        String weight_s = obj.getString("weight");
                        weight = Integer.parseInt(weight_s);
                        String height_s = obj.getString("height");
                        height = Integer.parseInt(height_s);
                        String target_weight_s = obj.getString("target_weight");
                        target_weight = Integer.parseInt(target_weight_s);
                        do_this(weight,height,target_weight);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });



        Button newgoal = findViewById(R.id.btnSetGoal);
        newgoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NewGoal.class);
                startActivityForResult(i, LAUNCH_SECOND_ACTIVITY);
            }
        });
    }

    public void do_this(Integer weight,Integer height, Integer target_weight){

        TextView currWeight = findViewById(R.id.currWeight);
        currWeight.setText("Current Weight: "+ weight);

        TextView currTargetWeight = findViewById(R.id.currTarget);
        currTargetWeight.setText("Target Weight: "+ target_weight);

        double bmi = weight / ((height/100.0) * (height/100.0));
        double roundOff = (double) Math.round(bmi*100)/100;
        TextView currBMI = findViewById(R.id.currBMI);
        currBMI.setText("Current BMI: "+ roundOff);

        double target_bmi = target_weight / ((height/100.0) * (height/100.0));
        double target_roundOff = (double) Math.round(target_bmi*100)/100;
        TextView targetBMI = findViewById(R.id.targetBMI);
        targetBMI.setText("Target BMI: " + target_roundOff);

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
                    new_weight = Integer.parseInt(result);
                    TextView currTargetWeight = findViewById(R.id.currTarget);
                    FirebaseDatabase.getInstance().getReference("Users").child(username).child("target_weight").setValue(Integer.toString(new_weight) );
                    currTargetWeight.setText("Target Weight: " + new_weight);

                    double target_bmi = new_weight *1.0 / ((height/100.0) * (height/100.0));
                    double target_roundOff = (double) Math.round(target_bmi*100)/100;

                    TextView targetBMI = findViewById(R.id.targetBMI);
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