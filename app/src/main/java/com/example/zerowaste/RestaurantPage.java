package com.example.zerowaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class RestaurantPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_restaurant_page2);
        Bundle bundle = intent.getBundleExtra("EXTRA_TEST");
       // Fragment RestaurantPageFragment = new Fragment();
        //RestaurantPageFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content3_frame, new RestaurantPageFragment()).commit();
        //transaction.add(R.id.content3_frame,RestaurantPageFragment).commit();
    }
}