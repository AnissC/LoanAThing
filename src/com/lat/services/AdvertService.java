package com.lat.services;

import com.lat.beans.*;

import com.lat.dao.*;
import com.lat.forms.AdvertAddForm;
import com.lat.forms.ApplyForm;
import com.lat.forms.LoanForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AdvertService
{
    private static AdvertService ADVERT_SERVICE = null;
    private HttpSession session;
    private AdvertDao advertDao;
    private ApplyDao applyDao;
    private CategoryDao categoryDao;
    private AdvertAddForm advertAddForm;
    private ApplyForm applyForm;
    private LoanDao loanDao;
    private LoanForm loanForm;

    private AdvertService()
    {
        this.advertDao = DAOFactory.getInstance().getAdvertDao();
        this.categoryDao = DAOFactory.getInstance().getCategoryDao();
        this.advertAddForm = new AdvertAddForm(this.advertDao);
        this.applyDao = DAOFactory.getInstance().getApplyDao();
        this.applyForm = new ApplyForm(this.applyDao);
        this.loanDao = DAOFactory.getInstance().getLoanDao();
        this.loanForm = new LoanForm(this.loanDao);
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

    public void updateAdvert(HttpServletRequest request)
    {
        Integer id = Integer.parseInt(getFieldValue(request, "id"));
        Advert advert = this.advertDao.findOneById(id);

        String title = getFieldValue(request, "title");
        String description = getFieldValue(request, "description");
        String dateStart = getFieldValue(request, "dateStart");
        String dateEnd = getFieldValue(request, "dateEnd");
        Integer categoryId = Integer.parseInt(getFieldValue(request, "categoryId"));
        Boolean isPublish = Boolean.parseBoolean(getFieldValue(request, "isPublish"));
        Boolean isSuspend = Boolean.parseBoolean(getFieldValue(request, "isSuspend"));

        this.advertAddForm.updateAdvert(advert, title, description, dateStart, dateEnd, categoryId, isPublish, isSuspend);
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

    public List<Advert> getAdvertsWithId(HttpServletRequest request)
    {
        this.session = request.getSession();
        User user = ((User) session.getAttribute("userSession"));
        Integer userId = user.getId();

        return this.advertDao.findAllByUserId(userId);
    }

    public List<Category> getCategories()
    {
        return this.categoryDao.find();
    }

    public LoanForm getLoanForm()
    {
        return this.loanForm;
    }

    public Loan processLoan(HttpServletRequest request)
    {
        Integer applyId = Integer.parseInt(getFieldValue(request, "applyId"));

        return this.loanForm.processLoan(applyId);
    }

    public Loan getLoan(HttpServletRequest request)
    {
        Integer id = Integer.parseInt(getFieldValue(request, "id"));

        return this.loanDao.findOneById(id);
    }

    public List<List<Apply>> getPendingRequests(HttpServletRequest request)
    {
        this.session = request.getSession();
        User user = ((User) session.getAttribute("userSession"));
        List<Advert> adverts = this.advertDao.findAllByUserId(user.getId());

        List<List<Apply>> applies = new ArrayList<List<Apply>>();
        for (Advert advert : adverts) {
            applies.add(this.applyDao.findPendingRequests(advert.getId()));
        }

        return applies;
    }

    public List<Apply> getInboxRequests(HttpServletRequest request)
    {
        this.session = request.getSession();
        User user = ((User) session.getAttribute("userSession"));

        return this.applyDao.findInboxRequests(user);
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

    public List<Advert> getAdvertsWithName(String name)
    {
        return advertDao.findAllByName("%" + name + "%");
    }

    public void ban(Advert advert) { advertDao.suspend(advert); }
}
