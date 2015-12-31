package com.lat.servlets;

import com.lat.beans.*;
import com.lat.services.AdvertService;
import com.lat.services.ReportingAdvertService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/backoffice/report/advert/ignore")
public class AdvertReportIgnore extends HttpServlet
{
    private ReportingAdvertService reportingAdvertService;
    private AdvertService advertService;

    public void init() throws ServletException
    {
        reportingAdvertService = ReportingAdvertService.getInstance();
        advertService = AdvertService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        com.lat.beans.Advert advert = advertService.getAdvert(request);

        if (advert != null)
        {
            reportingAdvertService.ignore(advert);
        }

        response.sendRedirect("/backoffice/report/adverts");
    }
}
