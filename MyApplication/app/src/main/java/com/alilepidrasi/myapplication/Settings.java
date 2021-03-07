package com.alilepidrasi.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.System.exit;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_settings);


        Button editProfile = (Button) findViewById(R.id.btnEditProfile);
        Button reevaluate = (Button) findViewById(R.id.btnReevaluate);
        Button connect = (Button) findViewById(R.id.btnConnectSmartDevices);
        Button coupons = (Button) findViewById(R.id.btnCoupons);
        Button logout = (Button) findViewById(R.id.btnLogout);

        reevaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "Re-evaluation will be added to our app in a few days, please be patient and check your coupons till then!";
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "Not currently available, make sure to check your coupons instead!";
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "Not currently available, make sure to check your coupons instead!";
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        coupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Coupons.class);
                startActivity(intent);
            }
        });

    }

}
