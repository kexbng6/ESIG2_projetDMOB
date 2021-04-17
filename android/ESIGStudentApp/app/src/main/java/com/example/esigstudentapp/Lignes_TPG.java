package com.example.esigstudentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Lignes_TPG extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lignes__t_p_g);

        webView = findViewById(R.id.webviewTPG);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.tpg.ch/fr/lignes");
        webView.getSettings().setJavaScriptEnabled(false);
        webView.setClickable(true);

        BottomNavigationView navBottom = findViewById(R.id.menu);
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bulletin:
                        startActivity(new Intent(Lignes_TPG.this, Bulletin.class));
                        break;
                    case R.id.calendrier:
                        startActivity(new Intent(Lignes_TPG.this, Calendrier.class));
                        break;
                    case R.id.horaire:
                        startActivity(new Intent(Lignes_TPG.this, Horaire.class));
                        break;
                }
                return true;
            }
        });
    }
}