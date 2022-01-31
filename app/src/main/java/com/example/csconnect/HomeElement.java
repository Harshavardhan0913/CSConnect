package com.example.csconnect;

public class HomeElement {
    private String heading;
    private String subheading;

    public HomeElement(String heading, String subheading) {
        this.heading = heading;
        this.subheading = subheading;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }
}
