package com.example.traveli;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter mAdapter;
    ArrayList<String> stringArrayList = new ArrayList<>();
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        populateRecyclerView();
        enableSwipeToDeleteAndUndo();

    }

    private void populateRecyclerView() {
        stringArrayList.add("Voyage 1");
        stringArrayList.add("Voyage 2");
        stringArrayList.add("Voyage 3");
        stringArrayList.add("Voyage 4");
        stringArrayList.add("Voyage 5");
        stringArrayList.add("Voyage 6");
        stringArrayList.add("Voyage 7");
        stringArrayList.add("Voyage 8");
        stringArrayList.add("Voyage 9");
        stringArrayList.add("Voyage 10");

        mAdapter = new RecyclerViewAdapter(stringArrayList);
        recyclerView.setAdapter(mAdapter);
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final String item = mAdapter.getData().get(position);


                /*
                mAdapter.removeItem(position);

                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item " + position + " was removed from the list", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                            mAdapter.restoreItem(item, position);
                            recyclerView.scrollToPosition(position);
                    }

                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
                */
            }

        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}
