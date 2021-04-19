package com.example.esigstudentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    Button tpg, esigWeb;
    ImageButton profilBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navBottom = findViewById(R.id.nav_view);
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bulletin:
                        startActivity(new Intent(MainActivity.this, Bulletin.class));
                        break;
                    case R.id.calendrier:
                        startActivity(new Intent(MainActivity.this, Calendrier.class));
                        break;
                    case R.id.horaire:
                        startActivity(new Intent(MainActivity.this, Horaire.class));
                        break;
                }
                return true;
            }
        });

        esigWeb = findViewById(R.id.esigWebBtn);
        esigWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ESIG_web.class));
            }
        });

        tpg = findViewById(R.id.tpgBtn);
        tpg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TPG.class));
            }
        });

        profilBtn = findViewById(R.id.profilBtn);
        profilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profil.class));
            }
        });
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        Toast.makeText(MainActivity.this, "Vous êtes déconnecté, merci pour votre visite !", Toast.LENGTH_SHORT).show();
        finish();
    }
}