package com.lat.services;

import com.lat.beans.ReportingUser;
import com.lat.beans.User;
import com.lat.dao.DAOFactory;
import com.lat.dao.ReportingUserDao;

import java.util.List;

public class ReportingUserService
{
    private static ReportingUserService REPORTING_USER_SERVICE = null;
    private ReportingUserDao reportingUserDao;

    private ReportingUserService()
    {
        this.reportingUserDao = DAOFactory.getInstance().getReportingUserDao();
    }

    public static ReportingUserService getInstance()
    {
        if (REPORTING_USER_SERVICE == null)
        {
            REPORTING_USER_SERVICE = new ReportingUserService();
        }

        return REPORTING_USER_SERVICE;
    }

    public List<ReportingUser> getAllReportingUsers()
    {
        return this.reportingUserDao.find();
    }

    public void reportUser(User user)
    {
        ReportingUser reportingUser  = new ReportingUser();
        reportingUser.setUser(user);
        this.reportingUserDao.create(reportingUser);
    }

    public void ignore(User user)
    {
        List<ReportingUser> reportingUsers = reportingUserDao.findAllByIdUser(user.getId());

        for (ReportingUser reportingUser : reportingUsers)
        {
            reportingUserDao.delete(reportingUser);
        }
    }
}
