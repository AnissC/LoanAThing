package com.lat.beans;

public class User
{
    private Long id;
    private String email;
    private String password;
    private String name;

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

    public void setName(String name)
    {
	    this.name = name;
    }

    public String getName()
    {
	    return name;
    }
}
