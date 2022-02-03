package com.example.csconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReviewsHome extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Subject> subjects;
    private DatabaseReference db;
    private String homeElement;
    private Intent intent1;
    private ProgressBar progressBar3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_home);
        subjects = new ArrayList<>();
        listView = findViewById(R.id.listView);
        Intent intent = getIntent();
        homeElement = intent.getStringExtra("HomeElement");

        progressBar3 = findViewById(R.id.progressBar3);

        db = FirebaseDatabase.getInstance().getReference(homeElement);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        Subject s = dataSnapshot.getValue(Subject.class);
                        subjects.add(s);
                    }
                }
                progressBar3.setVisibility(View.GONE);
                ReviewAdapter adapter = new ReviewAdapter(ReviewsHome.this,subjects);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent1 = new Intent(ReviewsHome.this, OpenReview.class);
                intent1.putExtra("HomeElement",homeElement);
                db = FirebaseDatabase.getInstance().getReference(homeElement);
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snapshot1: snapshot.getChildren()) {
                            Subject s = snapshot1.getValue(Subject.class);
                            if (subjects.get(i).getSubName().equals(s.getSubName())){
                                intent1.putExtra("SubName",subjects.get(i).getSubName());
                                intent1.putExtra("SId",snapshot1.getKey());

                            }
                        }
                        startActivity(intent1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
//                db.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
            }

        });





    }
}