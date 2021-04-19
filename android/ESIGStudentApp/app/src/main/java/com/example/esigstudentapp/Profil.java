package com.example.esigstudentapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profil extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference ref;
    private String userID;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    private Button logout;
    TextView prenom, nom, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        prenom = findViewById(R.id.prenomTxt);
        nom = findViewById(R.id.nomTxt);
        email = findViewById(R.id.emailTxt);


        fAuth = FirebaseAuth.getInstance();

        //user = FirebaseAuth.getInstance().getCurrentUser();
        //ref = FirebaseDatabase.getInstance().getReference("Users");
        userID = fAuth.getCurrentUser().getUid();

        fStore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = fStore.collection("Users").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                prenom.setText(value.getString("prenom"));
                nom.setText(value.getString("nom"));
                email.setText(value.getString("email"));
            }
        });

//        final EditText prenomTxt = (EditText) findViewById(R.id.userFirstNameTxt);
//        final EditText nomTxt = (EditText) findViewById(R.id.userNameTxt);
//        final EditText emailTxt = (EditText) findViewById(R.id.editEmailAddress);
//
//        ref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                User userProfile = dataSnapshot.getValue(User.class);
//                if(userProfile != null){
//                   String prenom = userProfile.prenom;
//                   String nom = userProfile.nom;
//                   String email = userProfile.email;
//
//                   prenomTxt.setText(prenom);
//                   nomTxt.setText(nom);
//                   emailTxt.setText(email);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(Profil.this, "Quelque chose cloche malheureusement", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        Toast.makeText(Profil.this, "Vous êtes déconnecté, merci pour votre visite !", Toast.LENGTH_SHORT).show();
        finish();
    }
}