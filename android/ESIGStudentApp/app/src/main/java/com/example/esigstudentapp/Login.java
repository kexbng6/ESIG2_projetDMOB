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

public class Login extends AppCompatActivity {
    EditText EmailLogin, passwordLogin;
    Button loginBtn;
    TextView createTxt;
    ProgressBar progressBarLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EmailLogin = findViewById(R.id.EmailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        progressBarLogin = findViewById(R.id.progressBar2);
        createTxt = findViewById(R.id.txtPasInscrit);
        loginBtn = findViewById(R.id.loginBtn);
        fAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EmailLogin.getText().toString().trim();
                String mdp = passwordLogin.getText().toString().trim();


                if (TextUtils.isEmpty(email)){
                    EmailLogin.setError("Veuillez remplir le champ email car il est requis");
                    return;
                }
                if (TextUtils.isEmpty(mdp)){
                    passwordLogin.setError("Le mot de passe est requis");
                    return;
                }
                if(mdp.length() < 8){
                    passwordLogin.setError("Le mot de passe doit être plus long que 8 caractères");
                }

                progressBarLogin.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email, mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Bienvenue, vous êtes connecté !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else{
                            Toast.makeText(Login.this, "Une erreur est survenue =(" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBarLogin.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        createTxt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Signup.class));
            }
        });

    }
}