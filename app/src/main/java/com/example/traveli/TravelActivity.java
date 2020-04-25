package com.example.traveli;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.traveli.Adapter.EventAdapter;
import com.example.traveli.Adapter.NoteAdapter;
import com.example.traveli.Helper.MyButtonClickListener;
import com.example.traveli.Helper.MySwipeHelper;
import com.example.traveli.Model.Event;
import com.example.traveli.Model.Note;
import com.example.traveli.Model.Travel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TravelActivity extends Activity {

    RecyclerView recyclerView;
    EventAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        Travel travel = (Travel) getIntent().getSerializableExtra("travel");
        /*
        Toolbar travelToolbar = findViewById(R.id.travelToolbar);
        travelToolbar.setTitle(travel.getName());*/
        ImageView header = findViewById(R.id.travelHeader);
        if(travel.getName().equals(getResources().getString(R.string.japanTravelName)))
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_japon,null));
        else if(travel.getName().equals(getResources().getString(R.string.chinaTravelName)))
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_chine,null));
        else if(travel.getName().equals(getResources().getString(R.string.usaTravelName)))
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_usa,null));

        CalendarView calendar = findViewById(R.id.calendarView);

        recyclerView = findViewById(R.id.eventRecyclerView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        final ImageView addEvent = findViewById(R.id.addEvent);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TravelActivity.this, "Add Event", Toast.LENGTH_SHORT).show();
            }
        });

        final ImageView returnHomePage = findViewById(R.id.homePage);
        returnHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TravelActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        MySwipeHelper mySwipeHelper = new MySwipeHelper(this, recyclerView, 200) {

            @Override
            public void instantiateMyButton(final RecyclerView.ViewHolder viewHolder, final List<MyButton> buffer) {
                buffer.add(new MyButton(
                        TravelActivity.this,
                        "Delete",
                        30,
                        R.mipmap.bin,
                        Color.parseColor("#FF3C30"),
                        new MyButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                removeEvent(pos);
                            }
                        }));

            }

        };

        mySwipeHelper.getSwipeEscapeVelocity(0.1f);
        mySwipeHelper.getSwipeVelocityThreshold(1f);

        generateEvent();


    }

    private void generateEvent() {

        List<Event> eventList = new ArrayList<>();
        Event event1 = new Event("Ascension du Mont Fuji", "04:00-", "09:00");
        Event event2 = new Event("Visite du quartier Shinjuku", "08:00-", "12:00");

        eventList.add(event1);
        eventList.add(event2);

        adapter = new EventAdapter(this, eventList);
        recyclerView.setAdapter(adapter);

    }

    private void removeEvent(int pos) {

        EventAdapter eventAdapter = (EventAdapter) recyclerView.getAdapter();
        eventAdapter.removeEvent(pos);
        eventAdapter.notifyItemRemoved(pos);

    }

}
