package com.example.esigstudentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TPG extends AppCompatActivity implements OnMapReadyCallback {

    Button lignesTPG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_p_g);

        SupportMapFragment map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapViewESIG);
        map.getMapAsync(this);

        lignesTPG = findViewById(R.id.lignesTPG);

        lignesTPG.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Lignes_TPG.class));
            }
        });

        BottomNavigationView navBottom = findViewById(R.id.menu);
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bulletin:
                        startActivity(new Intent(TPG.this, Bulletin.class));
                        break;
                    case R.id.calendrier:
                        startActivity(new Intent(TPG.this, Calendrier.class));
                        break;
                    case R.id.horaire:
                        startActivity(new Intent(TPG.this, Horaire.class));
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng arretGradelle1_9 = new LatLng(46.205766, 6.181112);
        googleMap.addMarker(new MarkerOptions().position(arretGradelle1_9).title("Arret Gradelle, lignes 1-9"));

        LatLng arretGradelle33 = new LatLng(46.204854, 6.179127);
        googleMap.addMarker(new MarkerOptions().position(arretGradelle33).title("Arret Gradelle, ligne 33"));

        LatLng arretGrangeCanal12_17 = new LatLng(46.199636, 6.174552);
        googleMap.addMarker(new MarkerOptions().position(arretGrangeCanal12_17).title("Arret Grange-Canal, lignes 12-17"));
    }
}