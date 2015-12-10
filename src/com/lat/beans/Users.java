package com.lat.beans;

import java.util.ArrayList;

public class Users {
    private Long id;
    private String lastname;
    private String firstname;
    private String nickname;
    private String email;
    private int nbStreet;
    private String address;
    private String city;
    private int zipCode;
    private String password;
    private String birthday;
    private String preferences;
    private String schoolDomain;
    private String educationFormation;
    private String img;
    private ArrayList<Users> friends;
    private Group group;



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public void setName(String lastNamename)
    {
        this.lastname = lastNamename;
    }

    public String getName()
    {
        return lastname;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getNickName() {
        return nickname;
    }

    public void setNickName(String nickName) {
        this.nickname = nickName;
    }

    public int getNbStreet() {
        return nbStreet;
    }

    public void setNbStreet(int nbStreet) {
        this.nbStreet = nbStreet;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getBirthDay() {
        return birthday;
    }

    public void setBirthDay(String birthDay) {
        this.birthday = birthDay;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getSchoolDomain() {
        return schoolDomain;
    }

    public void setSchoolDomain(String schoolDomain) {
        this.schoolDomain = schoolDomain;
    }

    public String getEducationFormation() {
        return educationFormation;
    }

    public void setEducationFormation(String educationFormation) {
        this.educationFormation = educationFormation;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ArrayList<Users> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Users> friends) {
        this.friends = friends;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
