package com.example.csconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addNewContest extends AppCompatActivity {
    private String homeElement;
    private TextView newContestBody,newContestHeading;
    private Button addNewContestButton;
    private DatabaseReference db;
    private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contest);
        Intent intent = getIntent();
        homeElement = intent.getStringExtra("HomeElement");
        newContestBody = findViewById(R.id.newContestBody);
        newContestHeading = findViewById(R.id.newContestHeading);
        addNewContestButton = findViewById(R.id.addNewContestButton);
        addNewContestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newContestHeading.getText().toString().length() == 0){
                    newContestHeading.setError("Enter Message");
                    newContestHeading.requestFocus();
                }
                else if(newContestBody.getText().toString().length() == 0){
                    newContestBody.setError("Enter Message");
                    newContestBody.requestFocus();
                }
                else{
                    currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    db = FirebaseDatabase.getInstance().getReference(homeElement);
                    Contest c = new Contest(newContestHeading.getText().toString(),newContestBody.getText().toString(),currentUser.getEmail());
                    String timestamp = Long.toString(System.currentTimeMillis());
                    db.child(timestamp).setValue(c);

                    Toast.makeText(addNewContest.this, "Contest Added Successfully!!!", Toast.LENGTH_SHORT).show();

                    Intent intent1 = new Intent(addNewContest.this,HomePage.class);
                    startActivity(intent1);
                }
            }
        });


    }
}