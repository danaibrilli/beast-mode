package com.alilepidrasi.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Coupons extends AppCompatActivity {
    ArrayList <String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_coupons);
        DatabaseReference mDatabase1 = FirebaseDatabase.getInstance().getReference();
        String username = "DummyUser";

        mDatabase1.child("Users").child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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
                        data_extraction(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });




    }
    public void data_extraction(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        JSONObject coupons = obj.getJSONObject("coupons");
        Log.d("coupons", json);
        String value;
        Iterator<String> keys = coupons.keys();
        while(keys.hasNext()) {
            Log.d("ΕΕΕ", "mphke sto while");
            String key = keys.next();
            Log.d("key", key);
            Log.d("ΕΕΕ", "mphke sto if");
            value = coupons.getString(key);
            arrayList.add(value + "$ Coupon from our stores");
        }

        ListView listView = (ListView) findViewById(R.id.list_view);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

    }


}