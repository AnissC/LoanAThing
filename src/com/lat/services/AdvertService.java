package com.lat.services;

import com.lat.beans.Advert;
import com.lat.dao.AdvertDao;
import com.lat.dao.DAOFactory;
import com.lat.dao.UserDao;
import com.lat.forms.AdvertAddForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdvertService
{
    private static AdvertService ADVERT_SERVICE = null;
    private HttpSession session;
    private UserDao userDao;
    private AdvertDao advertDao;
    private AdvertAddForm advertAddForm;

    private AdvertService()
    {
        this.userDao = DAOFactory.getInstance().getUserDao();
        this.advertDao = DAOFactory.getInstance().getAdvertDao();
        this.advertAddForm = new AdvertAddForm(this.advertDao);
    }

    public static AdvertService getInstance()
    {
        if (ADVERT_SERVICE == null)
        {
            ADVERT_SERVICE = new AdvertService();
        }

        return ADVERT_SERVICE;
    }

    public List<Advert> getAllAdverts()
    {
        return this.advertDao.find();
    }

    public AdvertAddForm getAdvertForm()
    {
        return this.advertAddForm;
    }

    public Advert processAdvert(HttpServletRequest request)
    {
        return this.advertAddForm.processAdvert(request);
    }

    public Advert getAdvert(int id)
    {
        return this.advertDao.findOneById(id);
    }
}
