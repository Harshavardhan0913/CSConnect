package com.example.csconnect;

public class UserReview {
    private String rating;
    private String comments;
    private String username;

    public UserReview() {
    }

    public UserReview(String rating, String comments, String username) {
        this.rating = rating;
        this.comments = comments;
        this.username = username;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
