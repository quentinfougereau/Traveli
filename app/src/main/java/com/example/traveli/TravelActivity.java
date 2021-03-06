package com.example.traveli;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveli.Adapter.EventAdapter;
import com.example.traveli.Helper.MyButtonClickListener;
import com.example.traveli.Helper.MySwipeHelper;
import com.example.traveli.Model.Event;
import com.example.traveli.Model.Travel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TravelActivity extends Activity {

    RecyclerView recyclerView;
    EventAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    float x1, x2, y1, y2;
    private Travel travel;

    String evenement1;
    String evenement2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        travel = (Travel) getIntent().getSerializableExtra("travel");

        ImageView header = findViewById(R.id.travelHeader);
        if(travel.getName().equals(getResources().getString(R.string.japanTravelName))) {
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_japon, null));
            evenement1 = "Ascension du Mont Fuji";
            evenement2 = "Visite du quartier Shinjuku";
        }
        else if(travel.getName().equals(getResources().getString(R.string.chinaTravelName))) {
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_chine, null));
            evenement1 = "Visite à Beijin";
            evenement2 = "Visite à Shanghai";
        }
        else if(travel.getName().equals(getResources().getString(R.string.usaTravelName))) {
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_usa, null));
            evenement1 = "Visite à NYC";
            evenement2 = "Road trip route 66";
        }

        CalendarView calendar = findViewById(R.id.calendarView);

        recyclerView = findViewById(R.id.eventRecyclerView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        final ImageView addEvent = findViewById(R.id.addEvent);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TravelActivity.this, AddEventActivity.class);
                intent.putExtra("travel", travel);
                startActivity(intent);
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
        Event event1 = new Event(evenement1, "04:00 -", "09:00");
        Event event2 = new Event(evenement2, "08:00 -", "12:00");

        eventList.add(event1);
        eventList.add(event2);

        adapter = new EventAdapter(this, eventList, travel);
        recyclerView.setAdapter(adapter);
    }

    private void removeEvent(int pos) {
        EventAdapter eventAdapter = (EventAdapter) recyclerView.getAdapter();
        eventAdapter.removeEvent(pos);
        eventAdapter.notifyItemRemoved(pos);
    }

    public boolean onTouchEvent(MotionEvent touchEvent) {

        switch (touchEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;

            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();

                if (x1 < x2) {
                    Intent intent = new Intent(this, NoteActivity.class);
                    intent.putExtra("travel", travel);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                } else {
                    Intent intent = new Intent(this, MapActivity.class);
                    intent.putExtra("travel", travel);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }

                break;
        }
        return false;

    }

}
