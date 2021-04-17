package com.example.esigstudentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Bulletin extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mFirestoreList;
    FirebaseAuth fAuth;


    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin);

        BottomNavigationView navBottom = findViewById(R.id.menu);
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bulletin:
                        startActivity(new Intent(Bulletin.this, Bulletin.class));
                        break;
                    case R.id.calendrier:
                        startActivity(new Intent(Bulletin.this, Calendrier.class));
                        break;
                    case R.id.horaire:
                        startActivity(new Intent(Bulletin.this, Horaire.class));
                        break;
                }
                return true;
            }
        });

        fAuth = FirebaseAuth.getInstance();
        String userId = fAuth.getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();
        mFirestoreList = findViewById(R.id.reclerview);

        //Query
        Query query = firebaseFirestore.collection("Module");//Query query = firebaseFirestore.collection(userId);
        //RecycleOptions
        FirestoreRecyclerOptions<Module> options = new FirestoreRecyclerOptions.Builder<Module>().setQuery(query, Module.class).build();

        adapter = new FirestoreRecyclerAdapter<Module, ModuleViewHolder>(options) {
            @NonNull
            @Override
            public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_module, parent, false);
                return new ModuleViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ModuleViewHolder holder, int position, @NonNull Module model) {
                holder.listName.setText(model.getCours());
                holder.listName.setText(model.getNote() + "");
            }
        };

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);
        //View Holder
    }

    private class ModuleViewHolder extends RecyclerView.ViewHolder {

        private TextView listName;
        private TextView listNote;

        public ModuleViewHolder(@NonNull View itemView) {
            super(itemView);

            listName = itemView.findViewById(R.id.list_name);
            listNote = itemView.findViewById(R.id.list_note);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}