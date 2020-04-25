package com.example.traveli;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveli.Adapter.NoteAdapter;
import com.example.traveli.Helper.MyButtonClickListener;
import com.example.traveli.Helper.MySwipeHelper;
import com.example.traveli.Model.Note;
import com.example.traveli.Model.Travel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NoteAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    float x1, x2, y1, y2;
    Travel travel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        travel = (Travel) getIntent().getSerializableExtra("travel");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        final FloatingActionButton addNote = findViewById(R.id.addNote);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NoteActivity.this, "Add Note", Toast.LENGTH_SHORT).show();
            }
        });

        MySwipeHelper mySwipeHelper = new MySwipeHelper(this, recyclerView, 200) {

            @Override
            public void instantiateMyButton(final RecyclerView.ViewHolder viewHolder, final List<MyButton> buffer) {
                buffer.add(new MyButton(
                        NoteActivity.this,
                        "Delete",
                        30,
                        R.drawable.ic_delete_white_24dp,
                        Color.parseColor("#FF3C30"),
                        new MyButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                removeNote(pos);
                            }
                        }));
            }

        };

        mySwipeHelper.getSwipeEscapeVelocity(0.1f);
        mySwipeHelper.getSwipeVelocityThreshold(1f);

        generateNote();

    }

    private void generateNote() {
        List<Note> noteList = new ArrayList<>();
        Note note1 = new Note("Les courses à faire", "Lorem ipsum...");
        Note note2 = new Note("Adresses utiles", "Lorem ipsum...");
        Note note3 = new Note("Numéro d'urgence", "Lorem ipsum...");

        noteList.add(note1);
        noteList.add(note2);
        noteList.add(note3);

        adapter = new NoteAdapter(this, noteList);
        recyclerView.setAdapter(adapter);
    }

    private void removeNote(int pos) {
        NoteAdapter noteAdapter = (NoteAdapter) recyclerView.getAdapter();
        noteAdapter.removeNote(pos);
        noteAdapter.notifyItemRemoved(pos);
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

                if (x1 > x2) {
                    Intent intent = new Intent(NoteActivity.this, TravelActivity.class);
                    intent.putExtra("travel", travel);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }

                break;
        }
        return false;

    }


}
