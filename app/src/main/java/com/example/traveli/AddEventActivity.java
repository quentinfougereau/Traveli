package com.example.traveli;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.widget.TimePicker;
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

import com.example.traveli.Model.Travel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity {
    private TextInputEditText nomEvenement;
    private TextInputEditText noteEvenement;

    private ImageView croix;
    private ImageView encocheGrise;
    private ImageView encocheVerte;

    private TextView heureAuEdit;
    private TextView heureDuEdit;

    private Travel travel;

    private static TextView selectionDateAu;
    private static TextView selectionDateDu;

    private static boolean estSelectionnerDateAu;
    private static boolean estSelectionnerDateDu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        travel = (Travel) getIntent().getSerializableExtra("travel");

        ImageView header = findViewById(R.id.travelHeader);
        if(travel.getName().equals(getResources().getString(R.string.japanTravelName)))
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_japon,null));
        else if(travel.getName().equals(getResources().getString(R.string.chinaTravelName)))
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_chine,null));
        else if(travel.getName().equals(getResources().getString(R.string.usaTravelName)))
            header.setImageDrawable(getResources().getDrawable(R.drawable.header_usa,null));

        nomEvenement = findViewById(R.id.nom_evenement);
        noteEvenement = findViewById(R.id.note_evenement);
        nomEvenement.addTextChangedListener(selectionWatcher);
        noteEvenement.addTextChangedListener(selectionWatcher);

        croix = (ImageView) findViewById(R.id.croix);
        encocheGrise = (ImageView) findViewById(R.id.encoche_grise);
        encocheVerte = (ImageView) findViewById(R.id.encoche_verte);

        selectionDateAu = (TextView) findViewById(R.id.auDate);
        selectionDateDu = (TextView) findViewById(R.id.duDate);

        heureAuEdit = (TextView) findViewById(R.id.heureAuEdit);
        heureDuEdit = (TextView) findViewById(R.id.heureDuEdit);

        heureAuEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        // Affichage d'un toast pour quitter en cliquant sur la croix
        croix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(AddEventActivity.this, TravelActivity.class);
                intent.putExtra("travel", travel);
                startActivity(intent);
            }
        });

        encocheVerte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(AddEventActivity.this, TravelActivity.class);
                intent.putExtra("travel", travel);
                startActivity(intent);
            }
        });

        selectionDateAu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estSelectionnerDateAu = true;
                AddEventActivity.DatePickerFragment datePickerFragment = new AddEventActivity.DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        selectionDateDu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estSelectionnerDateDu = true;
                AddEventActivity.DatePickerFragment datePickerFragment = new AddEventActivity.DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });


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