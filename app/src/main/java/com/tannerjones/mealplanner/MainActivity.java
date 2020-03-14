package com.tannerjones.mealplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Food Details
    // https://api.nal.usda.gov/fdc/v1/######?api_key=YOUR_API_KEY
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Search for New Meals
        findViewById(R.id.create_plan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FoodSearch.class);
                startActivity(intent);
            }
        });

        // Search for Saved Plans
        findViewById(R.id.view_plans).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), .class);
                //startActivity(intent);
            }
        });
    }
}
