package com.example.traveli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import com.example.traveli.Model.Travel;
import com.google.android.material.textfield.TextInputEditText;

public class AddNoteActivity extends AppCompatActivity {

    private TextInputEditText nomNote;
    private TextInputEditText note;

    private ImageView croix;
    private ImageView encocheGrise;
    private ImageView encocheVerte;

    private Travel travel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        travel = (Travel) getIntent().getSerializableExtra("travel");

        ImageView header = findViewById(R.id.travelHeader);
        if(travel.getName().equals(getResources().getString(R.string.japanTravelName)))
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_japon,null));
        else if(travel.getName().equals(getResources().getString(R.string.chinaTravelName)))
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_chine,null));
        else if(travel.getName().equals(getResources().getString(R.string.usaTravelName)))
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_usa,null));

        nomNote = findViewById(R.id.nom_note);
        note = findViewById(R.id.note);
        nomNote.addTextChangedListener(selectionWatcher);

        croix = (ImageView) findViewById(R.id.croix);
        encocheGrise = (ImageView) findViewById(R.id.encoche_grise);
        encocheVerte = (ImageView) findViewById(R.id.encoche_verte);

        // Affichage d'un toast pour quitter en cliquant sur la croix
        croix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(AddNoteActivity.this, NoteActivity.class);
                intent.putExtra("travel", travel);
                startActivity(intent);
            }
        });

        encocheVerte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(AddNoteActivity.this, NoteActivity.class);
                intent.putExtra("travel", travel);
                startActivity(intent);
            }
        });
    }

    private TextWatcher selectionWatcher = new TextWatcher() {
        // Pas utilisé
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        // Pas utilisé
        @Override
        public void afterTextChanged(Editable s) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nomEvenementInput = nomNote.getText().toString().trim();

            if(!nomEvenementInput.isEmpty()) {
                encocheVerte.setVisibility(View.VISIBLE);
                encocheGrise.setVisibility(View.INVISIBLE);
            }
            else {
                encocheVerte.setVisibility(View.INVISIBLE);
                encocheGrise.setVisibility(View.VISIBLE);
            }
        }
    };
}
