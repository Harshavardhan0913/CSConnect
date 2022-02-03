package com.example.csconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContestHome extends AppCompatActivity {
    private ListView listView;
    private Button addContestButton;
    private ArrayList<Contest> contests;
    private DatabaseReference db;
    private String homeElement;
    private ProgressBar progressBar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_home);
        addContestButton = findViewById(R.id.addContestbutton);
        contests = new ArrayList<>();
        listView = findViewById(R.id.listView);

        progressBar4 = findViewById(R.id.progressBar4);

        Intent intent = getIntent();
        homeElement = intent.getStringExtra("HomeElement");

        addContestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ContestHome.this,addNewContest.class);
                intent1.putExtra("HomeElement",homeElement);
                startActivity(intent1);
            }
        });


        db = FirebaseDatabase.getInstance().getReference(homeElement);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        Contest c = dataSnapshot.getValue(Contest.class);
                        contests.add(c);
                    }
                }
                listView = findViewById(R.id.listView);
                progressBar4.setVisibility(View.GONE);
                ContestsAdapter adapter = new ContestsAdapter(ContestHome.this,contests);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ContestHome.this,ShowContest.class);
                intent.putExtra("HomeElement",homeElement);
                db = FirebaseDatabase.getInstance().getReference(homeElement);
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot!=null){
                            for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                                Contest c = dataSnapshot.getValue(Contest.class);
                                String parent = dataSnapshot.getKey();
                                if (c.getHeading().toString().equals(contests.get(i).getHeading().toString())) {
                                    intent.putExtra("cId",parent);
                                }
                            }
                        }
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });



    }
}