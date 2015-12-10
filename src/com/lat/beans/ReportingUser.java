package com.lat.beans;


public class ReportingUser extends Reporting{

    private String references;
    private Users users;

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users user) {
        this.users = user;
    }
}
