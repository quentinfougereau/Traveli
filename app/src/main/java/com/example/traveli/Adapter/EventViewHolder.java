package com.example.traveli.Adapter;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveli.AddNoteActivity;
import com.example.traveli.Model.Event;
import com.example.traveli.Model.Travel;
import com.example.traveli.R;
import com.example.traveli.ShowEventActivity;

public class EventViewHolder extends RecyclerView.ViewHolder {

    Travel travel;
    Event event;
    TextView eventName, eventStartDate, eventEndDate;

    public EventViewHolder(@NonNull final View itemView) {
        super(itemView);
        eventName = (TextView) itemView.findViewById(R.id.eventName);
        eventStartDate = (TextView) itemView.findViewById(R.id.eventStartDate);
        eventEndDate = (TextView) itemView.findViewById(R.id.eventEndDate);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShowEventActivity.class);
                intent.putExtra("travel", travel);
                intent.putExtra("event", event);
                //final Intent intent = new Intent(v.getContext(), NoteActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
