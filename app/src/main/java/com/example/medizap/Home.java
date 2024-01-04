package com.example.medizap;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import com.google.android.material.bottomnavigation.BottomNavigationView;



public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    Map firstFragment = new Map();
    Appointment secondFragment = new Appointment();
    Tips thirdFragment = new Tips();
    Tracker fourthFragment = new Tracker();
    Reports fifthFragment = new Reports();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.Tracker);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.Tips) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, thirdFragment).commit();
            return true;
        } else if (itemId == R.id.Map) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, firstFragment).commit();
            return true;
        } else if (itemId == R.id.Appointment) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, secondFragment).commit();
            return true;
        } else if (itemId == R.id.Tracker) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fourthFragment).commit();
            return true;
        } else if (itemId == R.id.Reports) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fifthFragment).commit();
            return true;
        }

        return false;
    }
}
