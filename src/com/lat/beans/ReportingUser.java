package com.lat.beans;


public class ReportingUser extends Reporting{

    private String references;
    private Users user;

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
