package com.example.traveli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.traveli.Model.Travel;

public class TravelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Travel travel = (Travel) getIntent().getSerializableExtra("travel");

        TextView travelNameView = findViewById(R.id.travelName);
        travelNameView.setText(travel.getName());
    }
}
