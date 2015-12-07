package com.lat.beans;


public class State {
    private Long id;
    private String stateName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public boolean isAvailable(){
        return stateName == "available";
    }
}
