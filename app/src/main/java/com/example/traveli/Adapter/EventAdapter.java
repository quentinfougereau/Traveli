package com.example.traveli.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveli.Model.Event;
import com.example.traveli.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    Context context;
    List<Event> eventList;

    public EventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eventRowView = LayoutInflater.from(context)
                .inflate(R.layout.event_row_layout, parent, false);
        return new EventViewHolder(eventRowView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.eventName.setText(eventList.get(position).getName());
        holder.eventStartDate.setText(eventList.get(position).getStartDate());
        holder.eventEndDate.setText(eventList.get(position).getEndDate());
        holder.event = eventList.get(position);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public List<Event> getEventList() {
        return this.eventList;
    }

    public void removeEvent(int pos) {
        eventList.remove(pos);
    }

}
