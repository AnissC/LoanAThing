package com.lat.services;

import com.lat.beans.Advert;
import com.lat.beans.Apply;
import com.lat.beans.Category;
import com.lat.beans.User;

import com.lat.dao.*;
import com.lat.forms.AdvertAddForm;
import com.lat.forms.ApplyForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private ApplyForm applyForm;

    private AdvertService()
    {
        this.userDao = DAOFactory.getInstance().getUserDao();
        this.advertDao = DAOFactory.getInstance().getAdvertDao();
        this.applyDao = DAOFactory.getInstance().getApplyDao();
        this.categoryDao = DAOFactory.getInstance().getCategoryDao();
        this.advertAddForm = new AdvertAddForm(this.advertDao);
        this.applyForm = new ApplyForm(this.applyDao);
    }

    public static AdvertService getInstance()
    {
        if (ADVERT_SERVICE == null) {
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
        this.session = request.getSession();
        User user = ((User) session.getAttribute("userSession"));

        String title = getFieldValue(request, "title");
        String description = getFieldValue(request, "description");
        String dateStart = getFieldValue(request, "dateStart");
        String dateEnd = getFieldValue(request, "dateEnd");
        Integer categoryId = Integer.parseInt(getFieldValue(request, "categoryId"));
        Integer userId = user.getId();

        return this.advertAddForm.processAdvert(title, description, dateStart, dateEnd, categoryId, userId);
    }

    public Apply processApply(HttpServletRequest request) {
        this.session = request.getSession();
        User user = ((User) session.getAttribute("userSession"));

        String dateStart = getFieldValue(request, "dateStart");
        String dateEnd = getFieldValue(request, "dateEnd");
        Integer advertId = Integer.parseInt(getFieldValue(request, "advertId"));
        Integer userId = user.getId();

        return this.applyForm.processApply(dateStart, dateEnd, advertId, userId);
    }

    public Advert getAdvert(HttpServletRequest request)
    {
        Integer id = Integer.parseInt(getFieldValue(request, "id"));
        return this.advertDao.findOneById(id);
    }

    public List<Category> getCategories()
    {
        return this.categoryDao.find();
    }

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
