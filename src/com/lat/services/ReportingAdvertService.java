package com.lat.services;

import com.lat.beans.Advert;
import com.lat.beans.ReportingAdvert;
import com.lat.dao.*;
import com.lat.forms.AdvertAddForm;
import com.lat.forms.ApplyForm;
import com.lat.forms.LoanForm;

import javax.servlet.http.HttpSession;
import java.util.List;

public class ReportingAdvertService {

    private static ReportingAdvertService REPORTING_ADVERT_SERVICE = null;
    private ReportingAdvertDao reportingAdvertDao;

    private ReportingAdvertService()
    {
        this.reportingAdvertDao = DAOFactory.getInstance().getReportingAdvertDao();
    }

    public static ReportingAdvertService getInstance()
    {
        if (REPORTING_ADVERT_SERVICE == null) {
            REPORTING_ADVERT_SERVICE = new ReportingAdvertService();
        }

        return REPORTING_ADVERT_SERVICE;
    }

    public List<ReportingAdvert> getAllReportingAdverts()
    {
        return this.reportingAdvertDao.find();
    }

}
