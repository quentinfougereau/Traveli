package com.example.traveli.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveli.R;

class MyViewHolder extends RecyclerView.ViewHolder {

    TextView  travelName, travelDepartureDate, travelEndDate;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        travelName = (TextView) itemView.findViewById(R.id.travelName);
        travelDepartureDate = (TextView) itemView.findViewById(R.id.travelDepartureDate);
        travelEndDate = (TextView) itemView.findViewById(R.id.travelEndDate);
    }
}
