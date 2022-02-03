package com.example.csconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OpenReview extends AppCompatActivity {
    private String homeElement;
    private String subName;
    private String sId;
    private DatabaseReference db;
    private TextView subNameTextView;
    private Spinner rating;
    private TextView comments;
    private Button submitReview;
    private FirebaseUser currentUser;
    private String[] ratingCategory = {"1","2","3","4","5"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_review);
        Intent intent = getIntent();
        homeElement = intent.getStringExtra("HomeElement");
        subName = intent.getStringExtra("SubName");
        sId = intent.getStringExtra("SId");
        subNameTextView = findViewById(R.id.layoutShowSubName);
        rating = findViewById(R.id.layoutGetRating);
        comments = findViewById(R.id.layoutComments);
        submitReview = findViewById(R.id.layoutSubmitReview);
        subNameTextView.setText(subName);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();


        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ratingCategory);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rating.setAdapter(ad);

        submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(comments.getText().toString().length()!=0){

                    UserReview u = new UserReview(rating.getSelectedItem().toString(),comments.getText().toString(),currentUser.getEmail());
                    String timestamp = Long.toString(System.currentTimeMillis());
                    db = FirebaseDatabase.getInstance().getReference(homeElement).child(sId).child("userReviews");
                    db.child(timestamp).setValue(u);

                    Toast.makeText(OpenReview.this, "Review Submitted", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(OpenReview.this,HomePage.class);
                    startActivity(intent1);

                }
                else {
                    comments.setError("Enter Comments");
                    comments.requestFocus();

                }
            }
        });



    }
}