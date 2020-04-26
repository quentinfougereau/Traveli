package com.example.traveli.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveli.AddNoteActivity;
import com.example.traveli.Model.Note;
import com.example.traveli.Model.Travel;
import com.example.traveli.R;
import com.example.traveli.ShowNoteActivity;
import com.example.traveli.TravelActivity;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    Travel travel;
    Note note;
    TextView noteName;

    public NoteViewHolder(@NonNull final View itemView) {
        super(itemView);
        noteName = (TextView) itemView.findViewById(R.id.noteName);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShowNoteActivity.class);
                intent.putExtra("travel", travel);
                intent.putExtra("note", note);
                //final Intent intent = new Intent(v.getContext(), NoteActivity.class);
                v.getContext().startActivity(intent);
                /*
                final Intent intent = new Intent(v.getContext(), CalendarActivity.class);
                v.getContext().startActivity(intent);
                 */
            }
        });
    }

}
