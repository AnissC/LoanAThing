package com.lat.beans;


public class ReportingUser extends Reporting{

    private String references;
    private User user;

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
