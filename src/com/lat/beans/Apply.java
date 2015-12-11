package com.lat.beans;

import java.util.Date;

public class Apply {
    private Long id;
    private String startDate;
    private String endDate;
    private boolean accepted;
    private Users user;
    private Adverts advert;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Adverts getAdvert() {
        return advert;
    }

    public void setAdvert(Adverts advert) {
        this.advert = advert;
    }
}
