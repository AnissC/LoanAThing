package com.lat.services;


import com.lat.dao.AdvertDao;
import com.lat.dao.ApplyDao;
import com.lat.dao.DAOFactory;
import com.lat.dao.UserDao;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;

public class ApplyService
{
    private static ApplyService APPLY_SERVICE = null;
    private HttpSession session;
    private UserDao userDao;
    private AdvertDao advertDao;
    private ApplyDao applyDao;

    private ApplyService()
    {
        this.userDao = DAOFactory.getInstance().getUserDao();
        this.advertDao = DAOFactory.getInstance().getAdvertDao();
        this.applyDao = DAOFactory.getInstance().getApplyDao();
    }

    public static ApplyService getInstance()
    {
        if (APPLY_SERVICE == null) {
            APPLY_SERVICE = new ApplyService();
        }

        return APPLY_SERVICE;
    }



    public ResultSet nbApplies()
    {
        return this.applyDao.countApplies();
    }
}

