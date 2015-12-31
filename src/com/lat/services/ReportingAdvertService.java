package com.lat.services;

import com.lat.beans.Advert;
import com.lat.beans.ReportingAdvert;
import com.lat.dao.*;
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

    public void reportAdvert(Advert advert){
        ReportingAdvert reportingAdvert = new ReportingAdvert();
        reportingAdvert.setAdvert(advert);
        this.reportingAdvertDao.create(reportingAdvert);
    }

    public void ignore(Advert advert){
        List<ReportingAdvert> reportingAdverts = reportingAdvertDao.findAllByIdAdvert(advert.getId());

        for (ReportingAdvert reportingAdvert : reportingAdverts){
            reportingAdvertDao.delete(reportingAdvert);
        }
    }
}
