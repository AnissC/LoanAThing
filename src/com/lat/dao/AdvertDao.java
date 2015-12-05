package com.lat.dao;

import com.lat.beans.Advert;

public interface AdvertDao
{
    void create(Advert advert) throws DAOException;

    void delete(Advert advert) throws DAOException;

    Advert find(String title) throws DAOException;
}
