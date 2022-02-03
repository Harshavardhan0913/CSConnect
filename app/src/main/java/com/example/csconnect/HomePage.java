package com.example.csconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class HomePage extends AppCompatActivity {
    private Button logout;
    private ListView listView;
    private TextView homeUsername;
    private FirebaseAuth mAuth;
    private TextView homePageMessage;

//    private final HomeElement h1 = new HomeElement("College Forum","For Freshers and College related queries");
//    private final HomeElement h2 = new HomeElement("Placements Forum","For placement related queries");
//    private final HomeElement h3 = new HomeElement("Contests ","Programming contests etc.");
//    private final HomeElement h4 = new HomeElement("Reviews/Rating","College, Teachers, Subjects etc.");
//    private HomeElement[] homeElements = {h1,h2,h3,h4};
    String[] homeElements = {"College Forum","Placements Forum","Contests","Reviews/Rating"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        logout = findViewById(R.id.logout);
        homeUsername = findViewById(R.id.homeUsername);
        homePageMessage = findViewById(R.id.homePageMessage);
        homePageMessage.setText("Welcome to CSConnect");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String name = "";
        try {
            name = currentUser.getEmail();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        homeUsername.setText(name);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.listView);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(HomePage.this, android.R.layout.simple_list_item_1,homeElements);
        listView.setAdapter(ad);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent = new Intent(HomePage.this,CollegeForum.class);
                    intent.putExtra("HomeElement","collegeForum");
                    startActivity(intent);
                }
                else if(i==1){
                    Intent intent = new Intent(HomePage.this,CollegeForum.class);
                    intent.putExtra("HomeElement","placementsForum");
                    startActivity(intent);
                }
                else if(i==2){
                    Intent intent = new Intent(HomePage.this,ContestHome.class);
                    intent.putExtra("HomeElement","contests");
                    startActivity(intent);
                }
                else if(i==3){
                    Intent intent = new Intent(HomePage.this,ReviewsHome.class);
                    intent.putExtra("HomeElement","reviews");
                    startActivity(intent);
                }

            }
        });

    }
}