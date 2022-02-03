package com.example.csconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CollegeForum extends AppCompatActivity {
    private ListView listView;
    private DatabaseReference mDatabase;
    private Button newQuestionButton;
    private ArrayList<Question> questions;
    private DatabaseReference db;
    private String homeElement;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_forum);
        listView = findViewById(R.id.listView);
        Intent intent1 = getIntent();
        homeElement = intent1.getStringExtra("HomeElement");
        progressBar = findViewById(R.id.progressBar);

        newQuestionButton = findViewById(R.id.newQuestionButton);
        newQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CollegeForum.this,AddCollegeForumQuestion.class);
                intent.putExtra("HomeElement",homeElement);
                startActivity(intent);
            }
        });


        questions = new ArrayList<Question>();
        mDatabase = FirebaseDatabase.getInstance().getReference(homeElement);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Question qn =(Question) dataSnapshot.getValue(Question.class);
                    long ansCount = 0;
                    try{
                        if(dataSnapshot.hasChild("answers"))
                            ansCount = dataSnapshot.child("answers").getChildrenCount();

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    qn.setNoOfAnswers(ansCount);
                    questions.add(qn);
                    try {
                        Log.i("Test", qn.getQuestion());

                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                Log.i("Size",""+questions.size());
                try {
                    if(questions!=null) {
                        CollegeForumAdapter ad = new CollegeForumAdapter(CollegeForum.this, questions);
                        listView.setAdapter(ad);
                        progressBar.setVisibility(View.GONE);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent = new Intent(CollegeForum.this,CollegeForumOpenQuestion.class);
                intent.putExtra("HomeElement",homeElement);
                db = FirebaseDatabase.getInstance().getReference(homeElement);
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                            String parent = dataSnapshot.getKey();
                            Question q = dataSnapshot.getValue(Question.class);
                            if(questions.get(i).getQuestion().equals(q.getQuestion())){
                                Log.i("parent",parent);
                                intent.putExtra("qId",parent);
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