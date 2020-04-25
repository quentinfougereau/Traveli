package com.example.traveli.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveli.Model.Travel;
import com.example.traveli.R;

import java.util.List;

public class TravelAdapter extends RecyclerView.Adapter<TravelViewHolder> {

    Context context;
    List<Travel> travelList;

    public TravelAdapter(Context context, List<Travel> travelList) {
        this.context = context;
        this.travelList = travelList;
    }

    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View travelRowView = LayoutInflater.from(context)
                .inflate(R.layout.travel_row_layout, parent, false);
        return new TravelViewHolder(travelRowView);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelViewHolder holder, int position) {
        holder.travelName.setText(travelList.get(position).getName());
        holder.travelDepartureDate.setText(travelList.get(position).getDepartureDate());
        holder.travelEndDate.setText(travelList.get(position).getEndDate());
        holder.travel = travelList.get(position);
    }

    @Override
    public int getItemCount() {
        return travelList.size();
    }

    public List<Travel> getTravelList() {
        return this.travelList;
    }

    public void removeTravel(int pos) {
        travelList.remove(pos);
    }
}
