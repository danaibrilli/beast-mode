package com.alilepidrasi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Spin extends AppCompatActivity {
    int spin_num = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_spin);
        TextView tries = (TextView) findViewById(R.id.triesleft);
        ImageButton spin = (ImageButton) findViewById(R.id.btnwheel);
        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spin_num >0) {
                    spin_num--;
                    tries.setText("You have "+ spin_num+" tries left");
                    Random r = new Random();
                    int degree = 0, degree_old = 0;
                    int result;
                    degree_old = degree % 360;
                    degree = r.nextInt(360) + 720;
                    RotateAnimation rotate = new RotateAnimation(degree_old, degree,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotate.setDuration(3600);
                    rotate.setFillAfter(true);
                    rotate.setInterpolator(new DecelerateInterpolator());
                    rotate.setAnimationListener(new Animation.AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    int result = degree % 360;
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
}