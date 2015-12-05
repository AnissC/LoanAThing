package com.lat.dao;

import com.lat.beans.User;

public interface UserDao
{
    void create(User user) throws DAOException;

    User find(String email) throws DAOException;
}
