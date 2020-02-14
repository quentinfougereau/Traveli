package com.example.traveli.Adapter;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveli.CalendarActivity;
import com.example.traveli.MainActivity;
import com.example.traveli.R;

import static androidx.core.content.ContextCompat.startActivity;

class MyViewHolder extends RecyclerView.ViewHolder {

    TextView  travelName, travelDepartureDate, travelEndDate;

    public MyViewHolder(@NonNull final View itemView) {
        super(itemView);
        travelName = (TextView) itemView.findViewById(R.id.travelName);
        travelDepartureDate = (TextView) itemView.findViewById(R.id.travelDepartureDate);
        travelEndDate = (TextView) itemView.findViewById(R.id.travelEndDate);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(v.getContext(), CalendarActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
