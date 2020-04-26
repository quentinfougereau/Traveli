package com.example.traveli;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveli.Model.Note;
import com.example.traveli.Model.Travel;
import com.google.android.material.textfield.TextInputEditText;

public class ShowNoteActivity extends AppCompatActivity {

    private TextInputEditText nomNote;
    private TextInputEditText noteText;

    private ImageView croix;
    private ImageView encocheGrise;
    private ImageView encocheVerte;

    private Travel travel;
    private Note note;

    private static String note1 = "Les courses à faire";
    private static String note2 = "Adresses utiles";
    private static String note3 = "Numéro d'urgence";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);

        nomNote = findViewById(R.id.nom_note);
        noteText = findViewById(R.id.note);
        nomNote.addTextChangedListener(selectionWatcher);

        croix = (ImageView) findViewById(R.id.croix);
        encocheGrise = (ImageView) findViewById(R.id.encoche_grise);
        encocheVerte = (ImageView) findViewById(R.id.encoche_verte);

        travel = (Travel) getIntent().getSerializableExtra("travel");
        note = (Note) getIntent().getSerializableExtra("note");

        ImageView header = findViewById(R.id.travelHeader);
        if(travel.getName().equals(getResources().getString(R.string.japanTravelName))) {
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_japon, null));
        }
        else if(travel.getName().equals(getResources().getString(R.string.chinaTravelName))) {
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_chine, null));
        }
        else if(travel.getName().equals(getResources().getString(R.string.usaTravelName))) {
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_usa, null));
        }

        if(note.getName().equals(note1)) {
            nomNote.setText(note1);
            noteText.setText("1) Des oeufs\n2) Du pâtes");
        }
        else if(note.getName().equals(note2)) {
            nomNote.setText(note2);
            noteText.setText("163 Avenue de Luminy - 13288 Marseille");
        }
        else if(note.getName().equals(note3)) {
            nomNote.setText(note3);
            noteText.setText("1) 15\n2) 17\n3) 18");
        }

        // Affichage d'un toast pour quitter en cliquant sur la croix
        croix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ShowNoteActivity.this, NoteActivity.class);
                intent.putExtra("travel", travel);
                startActivity(intent);
            }
        });

        encocheVerte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ShowNoteActivity.this, NoteActivity.class);
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
