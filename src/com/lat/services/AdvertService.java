package com.lat.services;

import com.lat.beans.Adverts;
import com.lat.beans.Users;

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
        if (ADVERT_SERVICE == null) {
            ADVERT_SERVICE = new AdvertService();
        }

        return ADVERT_SERVICE;
    }

    public List<Adverts> getAllAdverts()
    {
        return this.advertDao.find();
    }

    public AdvertAddForm getAdvertForm()
    {
        return this.advertAddForm;
    }

    public Adverts processAdvert(HttpServletRequest request)
    {
        this.session = request.getSession();
        Users user = ((Users) session.getAttribute("userSession"));
        user = this.userDao.find(user.getEmail());
        String title = getFieldValue(request, "title");
        String description = getFieldValue(request, "description");
        String dateStart = getFieldValue(request, "dateStart");
        String dateEnd = getFieldValue(request, "dateEnd");

        return this.advertAddForm.processAdvert(title, description, dateStart, dateEnd, user);
    }

    public Adverts getAdvert(int id)
    {
        return this.advertDao.findOneById(id);
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getFieldValue(HttpServletRequest request, String fieldName)
    {
        String value = request.getParameter(fieldName);
        if (value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value.trim();
        }
    }
}
