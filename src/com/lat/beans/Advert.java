package com.lat.beans;

import java.util.Date;

public class Advert
{

    /* ======> Debut Attributs de classe <====== */

    private Long id;
    private String title;
    private String description;
    private String img;
    private Date dateStart;
    private Date dateEnd;
    private String simpleDescription;
    private User user;
    private Category category;
    private State state;

    /* ======> Fin Attributs de classe <====== */





    /* ======> Debut Getters & Setters de classe <====== */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSimpleDescription() {
        return simpleDescription;
    }

    public void setSimpleDescription(String simpleDescription) {
        this.simpleDescription = simpleDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    /* ======> Fin Getters & Setters de classe <====== */
}
