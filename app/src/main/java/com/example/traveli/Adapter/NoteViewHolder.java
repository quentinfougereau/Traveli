package com.example.traveli.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveli.R;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    TextView noteName;

    public NoteViewHolder(@NonNull final View itemView) {
        super(itemView);
        noteName = (TextView) itemView.findViewById(R.id.noteName);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                final Intent intent = new Intent(v.getContext(), CalendarActivity.class);
                v.getContext().startActivity(intent);
                 */
            }
        });
    }

}
