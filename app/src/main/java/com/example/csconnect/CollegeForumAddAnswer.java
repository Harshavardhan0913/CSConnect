package com.example.csconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CollegeForumAddAnswer extends AppCompatActivity {
    private TextView answerText;
    private DatabaseReference db;
    private Button addAnswer;
    private FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    private String homeElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_forum_add_answer);
        answerText = findViewById(R.id.answerText);
        addAnswer = findViewById(R.id.addAnswerButton);
        Intent intent = getIntent();
        homeElement = intent.getStringExtra("HomeElement");
        String qId = intent.getStringExtra("qId");

        addAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answerText.length()!=0) {
                    Answer ans = new Answer(answerText.getText().toString(), currentUser.getEmail());
                    long timestamp = System.currentTimeMillis();
                    db = FirebaseDatabase.getInstance().getReference(homeElement).child(qId).child("answers");
                    db.child(Long.toString(timestamp)).setValue(ans);
                    Intent intent1 = new Intent(CollegeForumAddAnswer.this, HomePage.class);
                    intent1.putExtra("HomeElement",homeElement);
                    startActivity(intent1);
                }
                else{
                    answerText.setError("Enter answer!!");
                    answerText.requestFocus();
                }
            }
        });







    }
}