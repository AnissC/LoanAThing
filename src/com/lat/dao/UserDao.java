package com.lat.dao;

import com.lat.beans.User;

import javax.jws.soap.SOAPBinding;

public interface UserDao
{
    void create(User user) throws DAOException;

    User find(String email) throws DAOException;
}
