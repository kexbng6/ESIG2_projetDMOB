package com.example.esigstudentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Bulletin extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mFirestoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin);

        firebaseFirestore = FirebaseFirestore.getInstance();
        mFirestoreList = findViewById(R.id.reclerview);

        //Query
        Query query = firebaseFirestore.collection("Module");
        //RecycleOptions
        FirestoreRecyclerOptions<Module> options = new FirestoreRecyclerOptions.Builder<Module>()
                .setQuery(query, Module.class)
                .build();
        //View Holder
    }
}