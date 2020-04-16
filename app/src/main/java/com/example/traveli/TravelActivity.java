package com.example.traveli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.traveli.Model.Travel;

public class TravelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Travel travel = (Travel) getIntent().getSerializableExtra("travel");

        Toolbar travelToolbar = findViewById(R.id.travelToolbar);
        travelToolbar.setTitle(travel.getName());

        CalendarView calendar = findViewById(R.id.calendarView);


    }
}
