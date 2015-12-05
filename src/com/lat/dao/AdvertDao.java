package com.lat.dao;

import com.lat.beans.Advert;

import java.util.ArrayList;

public interface AdvertDao
{
    void create(Advert advert) throws DAOException;

    void delete(Advert advert) throws DAOException;

    ArrayList find() throws DAOException;

    Advert findOneById(int id) throws DAOException;
}
