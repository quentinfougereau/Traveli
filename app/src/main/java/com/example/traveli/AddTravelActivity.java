package com.example.traveli;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class AddTravelActivity extends AppCompatActivity {
    private static final String[] PAYS = new String[]{
            "Argentine", "Belgique", "Canada", "Danmark", "Estonie", "France",
            "Gabon", "Hongrie", "Inde", "Japon", "Kenya", "Libye", "Malaisie",
            "Nepal", "Portugal", "Quatar", "Roumanie" , "Senegal", "Thailand",
            "Ukraine", "Venezuela", "Yemen", "Zimbabwe"
    };

    private TextInputEditText nomDuPaysEdit;
    private AutoCompleteTextView selectionPaysEdit;
    private ImageView croix;
    private ImageView encocheGrise;
    private ImageView encocheVerte;

    private static TextView selectionDateAu;
    private static TextView selectionDateDu;

    private static boolean estSelectionnerDateAu;
    private static boolean estSelectionnerDateDu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel);

        final AutoCompleteTextView actv = findViewById(R.id.actv); // Sélection d'un pays
        ImageView dots = (ImageView) findViewById(R.id.dots); // Flèche de sélection d'un pays

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, PAYS);
        actv.setAdapter(adapter); // Récupération de la liste des pays

        nomDuPaysEdit = findViewById(R.id.nom_pays);
        selectionPaysEdit = findViewById(R.id.actv);

        croix = (ImageView) findViewById(R.id.croix);
        encocheGrise = (ImageView) findViewById(R.id.encoche_grise);
        encocheVerte = (ImageView) findViewById(R.id.encoche_verte);

        nomDuPaysEdit.addTextChangedListener(selectionWatcher);
        selectionPaysEdit.addTextChangedListener(selectionWatcher);

        selectionDateAu = (TextView) findViewById(R.id.auDate);
        selectionDateDu = (TextView) findViewById(R.id.duDate);

        // Affichage des pays en cliquant sur la flèche
        dots.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                actv.showDropDown();
            }
        });

        // Affichage d'un toast pour quitter en cliquant sur la croix
        croix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(AddTravelActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        encocheVerte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(AddTravelActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        selectionDateAu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estSelectionnerDateAu = true;
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        selectionDateDu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estSelectionnerDateDu = true;
                DatePickerFragment datePickerFragment = new DatePickerFragment();
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
            String nomPaysInput = nomDuPaysEdit.getText().toString().trim();
            String selectionPaysInput = selectionPaysEdit.getText().toString().trim();

            if(!nomPaysInput.isEmpty() && !selectionPaysInput.isEmpty() && estSelectionnerDateAu && estSelectionnerDateDu) {
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