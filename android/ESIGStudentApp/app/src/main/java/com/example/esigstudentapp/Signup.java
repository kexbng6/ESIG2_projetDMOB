package com.example.esigstudentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText firstName, lastName, password, Email;
    Button registerBtn;
    TextView txtDejaInscrit;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtDejaInscrit = findViewById(R.id.txtPasInscrit);
        registerBtn = findViewById(R.id.loginBtn);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        password = findViewById(R.id.password);
        Email = findViewById(R.id.Email);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prenom = firstName.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String mdp = password.getText().toString().trim();

                if (TextUtils.isEmpty(prenom)){
                    firstName.setError("Veuillez renseigner un prénom dans ce champ");
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    Email.setError("Veuillez remplir le champ email car il est requis");
                    return;
                }
                if (TextUtils.isEmpty(mdp)){
                    password.setError("Le mot de passe est requis");
                    return;
                }
                if(mdp.length() < 8){
                    password.setError("Le mot de passe doit être plus long que 8 caractères");
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signup.this, "Le profil utilisateur de "+ prenom + " a été crée avec succès !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else{
                            Toast.makeText(Signup.this, "Une erreur est survenue =(" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
            }
        });

        txtDejaInscrit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}