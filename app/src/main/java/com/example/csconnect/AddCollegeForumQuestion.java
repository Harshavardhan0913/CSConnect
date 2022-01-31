package com.example.csconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class AddCollegeForumQuestion extends AppCompatActivity {
    private Button addQuestion;
    private TextView question;
    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    String homeElement;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_college_forum_question);

        Intent intent1 = getIntent();
        homeElement = intent1.getStringExtra("HomeElement");

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        addQuestion = findViewById(R.id.addQuestionButton);
        question = findViewById(R.id.question);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qn = question.getText().toString();
                if(qn.length()!=0) {
                    String username = "";
                    try {
                        username = currentUser.getEmail();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Question newQuestion = new Question(qn, username);
                    long timestamp = System.currentTimeMillis();
                    mDatabase.child(homeElement).child(Long.toString(timestamp)).setValue(newQuestion);
                    Toast.makeText(AddCollegeForumQuestion.this, "Question added successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddCollegeForumQuestion.this, HomePage.class);
                    startActivity(intent);
                }
                else {
                    question.setError("Enter Question");
                    question.requestFocus();
                }

            }
        });
    }


}