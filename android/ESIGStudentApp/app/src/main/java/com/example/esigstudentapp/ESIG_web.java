package com.example.esigstudentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ESIG_web extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_s_i_g_web);

        BottomNavigationView navBottom = findViewById(R.id.nav_view);
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bulletin:
                        startActivity(new Intent(ESIG_web.this, Bulletin.class));
                        break;
                    case R.id.calendrier:
                        startActivity(new Intent(ESIG_web.this, Calendrier.class));
                        break;
                    case R.id.horaire:
                        startActivity(new Intent(ESIG_web.this, Horaire.class));
                        break;
                }
                return true;
            }
        });

        webView = findViewById(R.id.esigWeb);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://edu.ge.ch/site/ecraymonduldry/esig/");
        webView.getSettings().setJavaScriptEnabled(false);
        webView.setClickable(true);
    }
}