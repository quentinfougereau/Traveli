package com.example.traveli.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveli.Model.Note;
import com.example.traveli.Model.Travel;
import com.example.traveli.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    Context context;
    List<Note> noteList;
    Travel travel;

    public NoteAdapter(Context context, List<Note> noteList, Travel travel) {
        this.context = context;
        this.noteList = noteList;
        this.travel = travel;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View noteRowView = LayoutInflater.from(context)
                .inflate(R.layout.note_row_layout, parent, false);
        return new NoteViewHolder(noteRowView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.noteName.setText(noteList.get(position).getName());
        holder.note = noteList.get(position);
        holder.travel = travel;
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public List<Note> getNoteList() {
        return this.noteList;
    }

    public void removeNote(int pos) {
        noteList.remove(pos);
    }

}
