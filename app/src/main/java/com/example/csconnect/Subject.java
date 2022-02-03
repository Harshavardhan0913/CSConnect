package com.example.csconnect;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Subject {
    private String subName;
    private HashMap<String,UserReview> userReviews;
    private  String sem;

    public Subject() {
    }

    public Subject(String subName,HashMap<String,UserReview> userReviews,String sem) {
        this.subName = subName;
        this.sem = sem;
        this.userReviews = userReviews;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public HashMap<String,UserReview> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(HashMap<String,UserReview> userReviews) {
        this.userReviews = userReviews;
    }
}
