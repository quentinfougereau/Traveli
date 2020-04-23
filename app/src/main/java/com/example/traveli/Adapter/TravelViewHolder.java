package com.example.traveli.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveli.MainActivity;
import com.example.traveli.Model.Travel;
import com.example.traveli.NoteActivity;
import com.example.traveli.R;
import com.example.traveli.TravelActivity;

class TravelViewHolder extends RecyclerView.ViewHolder {

    Travel travel;
    TextView  travelName, travelDepartureDate, travelEndDate;

    public TravelViewHolder(@NonNull final View itemView) {
        super(itemView);
        travelName = (TextView) itemView.findViewById(R.id.travelName);
        travelDepartureDate = (TextView) itemView.findViewById(R.id.travelDepartureDate);
        travelEndDate = (TextView) itemView.findViewById(R.id.travelEndDate);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TravelActivity.class);
                intent.putExtra("travel",travel);
                //final Intent intent = new Intent(v.getContext(), NoteActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
