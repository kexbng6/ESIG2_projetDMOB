package com.example.esigstudentapp;
//package com.example.projet_dmob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Calendrier extends AppCompatActivity {
    String cours, date, salle, type, heure;
    FirebaseAuth firebaseAuth;
    EditText editCours, editSalle, editType, editHeure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendrier);

        BottomNavigationView navBottom = findViewById(R.id.menu);
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bulletin:
                        startActivity(new Intent(Calendrier.this, Bulletin.class));
                        break;
                    case R.id.calendrier:
                        startActivity(new Intent(Calendrier.this, Calendrier.class));
                        break;
                    case R.id.horaire:
                        startActivity(new Intent(Calendrier.this, Horaire.class));
                        break;
                }
                return true;
            }
        });

        editCours = findViewById(R.id.edit_cours);
        editSalle = findViewById(R.id.edit_salle);
        editType = findViewById(R.id.edit_type);
        editHeure = findViewById(R.id.edit_heure);

        Button btncalendrier =findViewById(R.id.btncalendrier);
        CalendarView calendar =findViewById(R.id.calendarView2);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date= year +"/"+month+"/"+dayOfMonth;
                Toast.makeText(Calendrier.this,date,Toast.LENGTH_SHORT).show();

            }
        });

        btncalendrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GoogleSignInAccount acct= GoogleSignIn.getLastSignedInAccount(getBaseContext());
                //String email=acct.getEmail();
                String email="abc@gmail.com";
                Intent intent=new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE,"Cours");
                intent.putExtra(CalendarContract.Events.DESCRIPTION,"descritpion ");
                intent.putExtra(CalendarContract.Events.EXDATE,date);
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION,"x");
                intent.putExtra(Intent.EXTRA_EMAIL,email.toString());
                if (intent.resolveActivity(getPackageManager())!=null){
                    startActivity(intent);
                }
                else {

                }
                FirebaseFirestore mFireStore=FirebaseFirestore.getInstance();
                cours = editCours.getText().toString();
                salle = editSalle.getText().toString();
                type = editType.getText().toString();
                heure = editHeure.getText().toString();

                Examen examen = new Examen();

                firebaseAuth=FirebaseAuth.getInstance();

                //String email = null;

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // Name, email address, and profile photo Url
                    String name = user.getDisplayName();
                    email = user.getEmail();


                    // Check if user's email is verified
                    boolean emailVerified = user.isEmailVerified();

                    // The user's ID, unique to the Firebase project. Do NOT use this value to
                    // authenticate with your backend server, if you have one. Use
                    // FirebaseUser.getIdToken() instead.
                    String uid = user.getUid();
                }

                examen.setDate(date.trim());
                examen.setSalle(salle.trim().toString());
                examen.setCours(cours.trim().toString());
                examen.setType(type.trim().toString());
                examen.setHeure(heure.trim().toString());

                Map<String,String> map=new HashMap<>();
                map.put("date",date);
                map.put("emailUser",email);
                map.put("type",examen.getType());
                map.put("salle",examen.getSalle());
                map.put("cours",examen.getCours());
                map.put("heure",examen.getHeure());

                mFireStore.collection("Examen").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Calendrier.this, "Examen ajouté avec succès", Toast.LENGTH_LONG).show();
                        finish();
                        //startActivity(new Intent(calendrier.this, ListeHoraire_prof.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Calendrier.this, "Accès refusé", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}