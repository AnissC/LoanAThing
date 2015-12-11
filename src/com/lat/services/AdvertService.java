package com.lat.services;

import com.lat.beans.Adverts;
import com.lat.beans.Category;
import com.lat.beans.Users;

import com.lat.dao.*;
import com.lat.forms.AdvertAddForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AdvertService
{
    private static AdvertService ADVERT_SERVICE = null;
    private HttpSession session;
    private UserDao userDao;
    private AdvertDao advertDao;
    private ApplyDao applyDao;
    private CategoryDao categoryDao;
    private AdvertAddForm advertAddForm;

    private AdvertService()
    {
        this.userDao = DAOFactory.getInstance().getUserDao();
        this.advertDao = DAOFactory.getInstance().getAdvertDao();
        this.applyDao = DAOFactory.getInstance().getApplyDao();
        this.categoryDao = DAOFactory.getInstance().getCategoryDao();
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
        String title = getFieldValue(request, "title");
        String description = getFieldValue(request, "description");
        String dateStart = getFieldValue(request, "dateStart");
        String dateEnd = getFieldValue(request, "dateEnd");
        String categoryId = getFieldValue(request, "categoryId");

        return this.advertAddForm.processAdvert(title, description, dateStart, dateEnd, categoryId, user);
    }

    public Adverts getAdvert(int id)
    {
        return this.advertDao.findOneById(id);
    }

    public List<Category> getCategories()
    {
        return this.categoryDao.find();
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
