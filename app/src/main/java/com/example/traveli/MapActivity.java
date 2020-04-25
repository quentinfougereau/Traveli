package com.example.traveli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.traveli.Model.Travel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    float x1, x2, y1, y2;
    Travel travel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Toolbar travelToolbar = findViewById(R.id.travelMapToolbar);
        //travelToolbar.setTitle("La carte magique");

        travel = (Travel) getIntent().getSerializableExtra("travel");
        travelToolbar.setTitle(travel.getName());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng montPuget = new LatLng(43.2220, 5.4591);
        googleMap.addMarker(new MarkerOptions().position(montPuget)
                .title("Le Mont Puget"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(montPuget, (float) 13.0));

    }


    public boolean onTouchEvent(MotionEvent touchEvent) {

        switch (touchEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;

            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();

                if (x1 < x2) {
                    Intent intent = new Intent(MapActivity.this, TravelActivity.class);
                    intent.putExtra("travel", travel);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                }

                break;
        }
        return false;

    }

}
