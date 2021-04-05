package com.example.esigstudentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profil extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference ref;
    private String userID;

    private Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final EditText prenomTxt = (EditText) findViewById(R.id.userFirstNameTxt);
        final EditText nomTxt = (EditText) findViewById(R.id.userNameTxt);
        final EditText emailTxt = (EditText) findViewById(R.id.editEmailAddress);

        ref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userProfile = dataSnapshot.getValue(User.class);
                if(userProfile != null){
                   String prenom = userProfile.prenom;
                   String nom = userProfile.nom;
                   String email = userProfile.email;

                   prenomTxt.setText(prenom);
                   nomTxt.setText(nom);
                   emailTxt.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profil.this, "Quelque chose cloche malheureusement", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        //startActivity(new Intent(getApplicationContext(), Login.class));
        //finish();
    }

}