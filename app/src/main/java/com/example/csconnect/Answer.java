package com.example.csconnect;

public class Answer {
    private String answer;
    private String user;

    public Answer() {
    }

    public Answer(String answer, String user) {
        this.answer = answer;
        this.user = user;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
