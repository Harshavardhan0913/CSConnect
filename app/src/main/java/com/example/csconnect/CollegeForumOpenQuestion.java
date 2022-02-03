package com.example.csconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollegeForumOpenQuestion extends AppCompatActivity {
    private ListView listView;
    private TextView openQuestionQuestion,openQuestionUser;
    private Button newAnswerButton;
    private DatabaseReference db;
    private DatabaseReference db1;
    private String qId;
    private String homeElement;
    private ArrayList<Answer> answers;
    private ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_forum_open_question);
        answers = new ArrayList<>();
        listView = findViewById(R.id.listView);
        openQuestionQuestion = findViewById(R.id.openQuestionQuesion);
        openQuestionUser = findViewById(R.id.openQuestionUser);
        Intent intent = getIntent();
        homeElement = intent.getStringExtra("HomeElement");
        qId = intent.getStringExtra("qId");
        progressBar2 = findViewById(R.id.progressBar2);




        db = FirebaseDatabase.getInstance().getReference(homeElement).child(qId);
        db.addValueEventListener(new ValueEventListener() {
            @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
                Question q = snapshot.getValue(Question.class);
                try {
                    openQuestionQuestion.setText(q.getQuestion());
                    openQuestionUser.setText(q.getUsername());
                    if(snapshot.hasChild("answers")){
                        for(DataSnapshot snapshot1: snapshot.child("answers").getChildren()){
                            Answer a = snapshot1.getValue(Answer.class);
                            answers.add(a);
                        }
                    }
                    Log.i("answer_length",""+answers.size());
                    progressBar2.setVisibility(View.GONE);
                    CollegeForumAnswerAdapter adapter = new CollegeForumAnswerAdapter(CollegeForumOpenQuestion.this,answers);
                    listView.setAdapter(adapter);


                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        newAnswerButton = findViewById(R.id.newAnswerButton);
        newAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CollegeForumOpenQuestion.this,CollegeForumAddAnswer.class);
                intent.putExtra("qId",qId);
                intent.putExtra("HomeElement",homeElement);
                startActivity(intent);

            }
        });
    }
}