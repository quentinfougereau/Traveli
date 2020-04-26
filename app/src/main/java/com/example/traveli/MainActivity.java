package com.example.traveli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;

import com.example.traveli.Adapter.TravelAdapter;
import com.example.traveli.Helper.MyButtonClickListener;
import com.example.traveli.Helper.MySwipeHelper;
import com.example.traveli.Model.Travel;

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
                Intent intent = new Intent(MainActivity.this, AddTravelActivity.class);
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

        for (int i = 0; i < 3; i++) {
            Travel japon = new Travel(getResources().getString(R.string.japanTravelName), "Du 04/0"+(2+i)+"/202"+i, "Au 16/0"+(2+i)+"/202"+i);
            Travel chine = new Travel(getResources().getString(R.string.chinaTravelName), "Du 06/0"+(5+i)+"/202"+i, "Au 15/0"+(6+i)+"/202"+i);
            Travel usa = new Travel(getResources().getString(R.string.usaTravelName), "Du 21/0"+(6+i)+"/202"+i, "Au 10/1"+(7+i)+"/202"+i);

            travelList.add(japon);
            travelList.add(chine);
            travelList.add(usa);
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
