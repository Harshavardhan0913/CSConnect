package com.example.csconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowContest extends AppCompatActivity {
    private TextView showContestHeading, showContestBody,showContestUser;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contest);
        showContestHeading = findViewById(R.id.showContestHeading);
        showContestBody = findViewById(R.id.showContestBody);
        showContestUser = findViewById(R.id.showContestUser);

        Intent intent = getIntent();
        String homeElement = intent.getStringExtra("HomeElement");
        String cId = intent.getStringExtra("cId");

        db = FirebaseDatabase.getInstance().getReference(homeElement).child(cId);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot!= null){
                    Contest c = snapshot.getValue(Contest.class);
                    showContestHeading.setText(c.getHeading());
                    showContestBody.setText(c.getBody());
                    showContestUser.setText(c.getUserName());
                    showContestBody.setKeyListener(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}