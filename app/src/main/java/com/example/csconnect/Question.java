package com.example.csconnect;

import java.util.ArrayList;
import java.util.HashMap;

public class Question {
    private String question;
    private String username;
    private HashMap<String,Answer> answers;
    private long noOfAnswers = 0;


    public Question() {
    }
    public Question(String question, String username) {
        this.question = question;
        this.username = username;
    }

    public Question(String question, int noOfAnswers, String username, HashMap<String,Answer> answers) {
        this.question = question;
        this.username = username;
        this.answers = answers;
        this.noOfAnswers = noOfAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashMap<String,Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<String,Answer> answers) {
        this.answers = answers;
    }


    public long getNoOfAnswers() {
        return noOfAnswers;
    }

    public void setNoOfAnswers(long noOfAnswers) {
        this.noOfAnswers = noOfAnswers;
    }


}
