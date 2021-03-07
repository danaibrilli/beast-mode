package com.alilepidrasi.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class Spin extends AppCompatActivity {
    int spin_num = 5;
    MediaPlayer player;
    int result;
    String username = "DummyUser";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_spin);



        TextView tries = (TextView) findViewById(R.id.triesleft);
        tries.setText("You have ... tries left");

        CharSequence text = "Getting your data from our database,please wait";
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
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
                        String spin_num_s = obj.getString("spin_num");
                        spin_num = Integer.parseInt(spin_num_s);

                        do_this();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });


    }

    public void do_this(){
        TextView tries = (TextView) findViewById(R.id.triesleft);
        tries.setText("You have "+ spin_num+" tries left");
        ImageButton spin = (ImageButton) findViewById(R.id.btnwheel);
        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spin_num >0) {
                    spin_num--;
                    FirebaseDatabase.getInstance().getReference("Users").child(username).child("spin_num").setValue(Integer.toString(spin_num) );
                    tries.setText("You have "+ spin_num+" tries left");
                    Random r = new Random();
                    int degree = 0, degree_old = 0;

                    degree_old = degree % 360;
                    degree = r.nextInt(360) + 720;
                    result = (degree % 20) * 5;
                    RotateAnimation rotate = new RotateAnimation(degree_old, degree,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotate.setDuration(3600);
                    rotate.setFillAfter(true);
                    rotate.setInterpolator(new DecelerateInterpolator());
                    rotate.setAnimationListener(new Animation.AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {
                            if (player == null){
                                player = MediaPlayer.create(getApplicationContext(),R.raw.wheel_sound);
                                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        if (player != null){
                                            player.release();
                                            player = null;
                                        }
                                    }
                                });
                            }
                            player.start();
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (player != null){
                                player.release();
                                player = null;
                                afterAnimation(result);
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    spin.startAnimation(rotate);

                }
                else{
                    CharSequence text = "No more tries left!!";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }



    public void afterAnimation(int result){
        if (result >= 50 || result ==0) {
            alertNoPrize();
        }
        else{
            final int[] ids = {0};
            alertPrize();
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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
                            String ids_s = obj.getString("ids");
                            ids[0] = Integer.parseInt(ids_s);
                            ids[0]++;
                            FirebaseDatabase.getInstance().getReference("ids").setValue(Integer.toString(ids[0]));
                            FirebaseDatabase.getInstance().getReference("Users").child(username).child("coupons").child("id00"+ids[0]).setValue(Integer.toString(result) );

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });




        }




    }

    public void alertPrize(){
        CharSequence text = "You won a " +result+ "$ coupon from our stores!!";
        AlertDialog.Builder builder = new AlertDialog.Builder(Spin.this);
        builder.setTitle("Congratulations!!");
        builder.setIcon(R.drawable.confetti);
        builder.setMessage(text);
        builder.setPositiveButton("Thank you!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void alertNoPrize(){
        CharSequence text = "Sorry, you didn't win a coupon.Try again next time!";
        AlertDialog.Builder builder = new AlertDialog.Builder(Spin.this);
        builder.setTitle("Oh no!");
        builder.setIcon(R.drawable.sad);
        builder.setMessage(text);
        builder.setPositiveButton("Okay!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}