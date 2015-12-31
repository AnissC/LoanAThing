package com.lat.servlets;

import com.lat.beans.ReportingAdvert;
import com.lat.services.AdvertService;
import com.lat.services.ReportingAdvertService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/advert/report")
public class ReportAdvert extends HttpServlet
{
    private AdvertService advertService;
    private ReportingAdvertService reportingAdvertService;

    public void init() throws ServletException
    {
        this.advertService = AdvertService.getInstance();
        this.reportingAdvertService = ReportingAdvertService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        com.lat.beans.Advert advert = advertService.getAdvert(request);
        if(advert != null){
            reportingAdvertService.reportAdvert(advert);
        }
        response.sendRedirect("/home");
    }
}
