package com.example.traveli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.traveli.Adapter.TravelAdapter;
import com.example.traveli.Helper.MyButtonClickListener;
import com.example.traveli.Helper.MySwipeHelper;
import com.example.traveli.Model.Travel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TravelAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        final ImageView addTravel = findViewById(R.id.addTravel);
        addTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTravel.class);
                startActivity(intent);
            }
        });

        MySwipeHelper mySwipeHelper = new MySwipeHelper(this, recyclerView, 200) {

            @Override
            public void instantiateMyButton(final RecyclerView.ViewHolder viewHolder, final List<MySwipeHelper.MyButton> buffer) {
                buffer.add(new MyButton(
                        MainActivity.this,
                        "Delete",
                        30,
                        R.mipmap.bin,
                        Color.parseColor("#FF3C30"),
                        new MyButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                removeTravel(pos);
                            }
                        }));

                /*
                buffer.add(new MyButton(
                        MainActivity.this,
                        "Update",
                        30,
                        R.drawable.ic_edit_black_24dp,
                        Color.parseColor("#FF9502"),
                        new MyButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                Toast.makeText(MainActivity.this, "Update click", Toast.LENGTH_SHORT).show();
                            }
                        }));
                 */
            }

        };

        mySwipeHelper.getSwipeEscapeVelocity(0.1f);
        mySwipeHelper.getSwipeVelocityThreshold(1f);

        generateTravel();
    }

    private void generateTravel() {
        List<Travel> travelList = new ArrayList<>();
        Travel japon = new Travel(getResources().getString(R.string.japanTravelName), "Du 04/02/2020", "Au 16/05/2020");
        Travel chine = new Travel(getResources().getString(R.string.chinaTravelName), "Du 06/07/2020", "Au 15/08/2020");
        Travel usa = new Travel(getResources().getString(R.string.usaTravelName), "Du 21/06/2020", "Au 10/12/2020");

        for (int i = 0; i < 20; i++) {
            if (i % 3 == 0) {
                travelList.add(japon);
            } else if (i % 3 == 1) {
                travelList.add(chine);
            } else {
                travelList.add(usa);
            }
        }

        adapter = new TravelAdapter(this, travelList);
        recyclerView.setAdapter(adapter);
    }

    private void removeTravel(int pos) {
        TravelAdapter travelAdapter = (TravelAdapter) recyclerView.getAdapter();
        travelAdapter.removeTravel(pos);
        travelAdapter.notifyItemRemoved(pos);
    }
}
