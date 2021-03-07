package com.alilepidrasi.myapplication;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User {

    public String username;
    public int height;
    public int weight;
    public int reps_push_ups;
    int target_Weight;

    public Map<String, Integer> previous_weights = new HashMap<>();
    public Map<String, Integer> coupons = new HashMap<>();

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public User(String username,Integer height, Integer weight, Integer reps_push_ups, Integer target_Weight) {
        this.username = username;
        this.weight = weight;
        this.reps_push_ups = reps_push_ups;
        this.target_Weight = target_Weight;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username",username);
        result.put("height", height);
        result.put("weight", weight);
        result.put("target_weight", target_Weight);
        result.put("reps_push_ups", reps_push_ups);
        result.put("previous_weights", previous_weights);
        result.put("coupons", coupons);

        return result;
    }
}