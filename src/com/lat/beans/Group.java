package com.lat.beans;

public class Group {
    private Integer id;
    private String name;
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
