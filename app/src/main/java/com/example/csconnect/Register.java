package com.example.csconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Register extends AppCompatActivity {
    private TextView regUsername,regPassword,regEmail;
    private Button registerBtn;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();


        regUsername = findViewById(R.id.regUserName);
        regPassword = findViewById(R.id.regPassword);
        registerBtn = findViewById(R.id.regButton);
        regEmail = findViewById(R.id.regEmail);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = regUsername.getText().toString();
                String email = regEmail.getText().toString().trim();
                String password = regPassword.getText().toString().trim();
                Boolean correctLogin = checkEmailPassword(email,password,userName);
                if(correctLogin) addUser(email,password);
            }
        });

    }

    private Boolean checkEmailPassword(String email, String password,String userName) {
        Boolean isCorrect = false;
        if (userName.length()==0){
            regUsername.setError("Enter UserName");
            regUsername.requestFocus();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            regEmail.setError("Wrong email format");
            regEmail.requestFocus();
        }
        else if(password.length()<6){
            regPassword.setError("Password too small(Min. Characters is 6)");
            regPassword.requestFocus();
        }
        else{
            isCorrect = true;
        }
        return isCorrect;
    }

    private void addUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(regUsername.getText().toString()).build();
                            user.updateProfile(profileUpdates);

                            goToHome();


                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Register.this, "Authentication failed.",Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

    private void goToHome() {
        Intent intent = new Intent(Register.this,HomePage.class);
        startActivity(intent);
    }

}