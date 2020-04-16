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

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Travel> travelList;

    public MyAdapter(Context context, List<Travel> travelList) {
        this.context = context;
        this.travelList = travelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View travelRowView = LayoutInflater.from(context)
                .inflate(R.layout.travel_row_layout, parent, false);
        return new MyViewHolder(travelRowView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
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
