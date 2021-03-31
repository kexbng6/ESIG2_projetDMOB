package com.example.esigstudentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    EditText firstName, lastName, password, Email;
    Button registerBtn;
    TextView txtDejaInscrit;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtDejaInscrit = findViewById(R.id.txtDejaInscrit);
        registerBtn = findViewById(R.id.registerBtn);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        password = findViewById(R.id.password);
        Email = findViewById(R.id.Email);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String mdp = password.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Email.setError("Veuillez remplir le email est requis");
                    return;
                }
                if (TextUtils.isEmpty(mdp)){
                    password.setError("Le mot de passe est requis");
                    return;
                }
                if(mdp.length() < 8){
                    password.setError("Le mot de passe doit être plus long que 8 caractères");
                }
            }
        });
    }
}