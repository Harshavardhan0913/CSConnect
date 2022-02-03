package com.example.csconnect;

public class Contest {
    private String heading,body,userName;
    public Contest() {
    }

    public Contest(String heading, String body, String userName) {
        this.heading = heading;
        this.body = body;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
