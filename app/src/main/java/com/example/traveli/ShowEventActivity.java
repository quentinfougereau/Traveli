package com.example.traveli;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.traveli.Model.Event;
import com.example.traveli.Model.Travel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class ShowEventActivity extends AppCompatActivity {
    private TextInputEditText nomEvenement;
    private TextInputEditText noteEvenement;

    private ImageView croix;
    private ImageView encocheGrise;
    private ImageView encocheVerte;

    private Travel travel;
    private Event event;

    private static String eventJapon1 = "Ascension du Mont Fuji";
    private static String eventJapon2 = "Visite du quartier Shinjuku";

    private static String eventChine1 = "Visite à Beijin";
    private static String eventChine2 = "Visite à Shanghai";

    private static String eventUsa1 = "Visite à NYC";
    private static String eventUsa2 = "Road trip route 66";

    private static TextView selectionDateAu;
    private static TextView selectionDateDu;

    private static boolean estSelectionnerDateAu;
    private static boolean estSelectionnerDateDu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        travel = (Travel) getIntent().getSerializableExtra("travel");
        event = (Event) getIntent().getSerializableExtra("event");

        nomEvenement = findViewById(R.id.nom_evenement);
        noteEvenement = findViewById(R.id.note_evenement);

        ImageView header = findViewById(R.id.travelHeader);
        if(travel.getName().equals(getResources().getString(R.string.japanTravelName)))  {
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_japon, null));
            if(event.getName().equals(eventJapon1)) {
                nomEvenement.setText(eventJapon1);
                noteEvenement.setText("Ascension");
            }
            else if(event.getName().equals(eventJapon2)) {
                nomEvenement.setText(eventJapon2);
                noteEvenement.setText("Visite rapide du quartier");
            }
        }
        else if(travel.getName().equals(getResources().getString(R.string.chinaTravelName))) {
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_chine, null));
            if(event.getName().equals(eventChine1)) {
                nomEvenement.setText(eventChine1);
                noteEvenement.setText("Visite rapide du quartier");
            }
            else if(event.getName().equals(eventChine2)) {
                nomEvenement.setText(eventChine2);
                noteEvenement.setText("Visite rapide du quartier");
            }
        }
        else if(travel.getName().equals(getResources().getString(R.string.usaTravelName))) {
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_usa, null));
            if(event.getName().equals(eventUsa1)) {
                nomEvenement.setText(eventUsa1);
                noteEvenement.setText("Visite rapide du quartier");
            }
            else if(event.getName().equals(eventUsa2)) {
                nomEvenement.setText(eventUsa2);
                noteEvenement.setText("Road trip en moto");
            }
        }

        nomEvenement.addTextChangedListener(selectionWatcher);
        noteEvenement.addTextChangedListener(selectionWatcher);

        croix = (ImageView) findViewById(R.id.croix);
        encocheGrise = (ImageView) findViewById(R.id.encoche_grise);
        encocheVerte = (ImageView) findViewById(R.id.encoche_verte);

        selectionDateAu = (TextView) findViewById(R.id.auDate);
        selectionDateDu = (TextView) findViewById(R.id.duDate);

        selectionDateDu.setText("20/4/2020");
        selectionDateAu.setText("30/4/2020");

        encocheVerte.setVisibility(View.VISIBLE);
        encocheGrise.setVisibility(View.INVISIBLE);

        // Affichage d'un toast pour quitter en cliquant sur la croix
        croix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ShowEventActivity.this, TravelActivity.class);
                intent.putExtra("travel", travel);
                startActivity(intent);
            }
        });

        encocheVerte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ShowEventActivity.this, TravelActivity.class);
                intent.putExtra("travel", travel);
                startActivity(intent);
            }
        });

        selectionDateAu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estSelectionnerDateAu = true;
                AddTravelActivity.DatePickerFragment datePickerFragment = new AddTravelActivity.DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        selectionDateDu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estSelectionnerDateDu = true;
                AddTravelActivity.DatePickerFragment datePickerFragment = new AddTravelActivity.DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

    public static void changeContent() {

    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener  {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month = month + 1;
            String date = dayOfMonth + "/" + month + "/" + year;
            if(estSelectionnerDateAu) {
                selectionDateAu.setText(date);
            }
            else if(estSelectionnerDateDu) {
                selectionDateDu.setText(date);
            }
        }
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
            String nomEvenementInput = nomEvenement.getText().toString().trim();
            String noteEvenementInput = noteEvenement.getText().toString().trim();

            if(!nomEvenementInput.isEmpty() && !noteEvenementInput.isEmpty()) {
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