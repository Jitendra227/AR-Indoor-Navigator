package com.example.indoornavigator.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.indoornavigator.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TestingActivity extends AppCompatActivity {

    ExtendedFloatingActionButton floor;
    FloatingActionButton mf0, mf1, mf2, mf3, mf4, mf5;
    TextView f0,f1,f2,f3,f4,f5;
    boolean isAllFabsVisible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        //binding
        floor = findViewById(R.id.floor);
        mf0 = findViewById(R.id.ground_floor);
        mf1 = findViewById(R.id.first_floor);
        mf2 = findViewById(R.id.second_floor);
        mf3 = findViewById(R.id.third_floor);
        mf4 = findViewById(R.id.fourth_floor);
        mf5 = findViewById(R.id.fifth_floor);

        f0 = findViewById(R.id.ground_floor_text);
        f1 = findViewById(R.id.first_floor_text);
        f2 = findViewById(R.id.second_floor_text);
        f3 = findViewById(R.id.third_floor_text);
        f4 = findViewById(R.id.fourth_floor_text);
        f5 = findViewById(R.id.fifth_floor_text);

        mf0.setVisibility(View.GONE);
        mf1.setVisibility(View.GONE);
        mf2.setVisibility(View.GONE);
        mf3.setVisibility(View.GONE);
        mf4.setVisibility(View.GONE);
        mf5.setVisibility(View.GONE);

        f0.setVisibility(View.GONE);
        f1.setVisibility(View.GONE);
        f2.setVisibility(View.GONE);
        f3.setVisibility(View.GONE);
        f4.setVisibility(View.GONE);
        f5.setVisibility(View.GONE);

        isAllFabsVisible = false;

        floor.shrink();

        floor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isAllFabsVisible) {
                    mf0.show();mf1.show();mf2.show();mf3.show();mf4.show();mf5.show();
                    f0.setVisibility(View.VISIBLE);
                    f1.setVisibility(View.VISIBLE);
                    f2.setVisibility(View.VISIBLE);
                    f3.setVisibility(View.VISIBLE);
                    f4.setVisibility(View.VISIBLE);
                    f5.setVisibility(View.VISIBLE);

                    floor.extend();
                    isAllFabsVisible = true;
                }
                else {
                    mf0.hide();mf1.hide();mf2.hide();mf3.hide();mf4.hide();mf5.hide();
                    f0.setVisibility(View.GONE);
                    f1.setVisibility(View.GONE);
                    f2.setVisibility(View.GONE);
                    f3.setVisibility(View.GONE);
                    f4.setVisibility(View.GONE);
                    f5.setVisibility(View.GONE);

                    floor.shrink();
                    isAllFabsVisible = false;
                }
            }
        });



    }
}