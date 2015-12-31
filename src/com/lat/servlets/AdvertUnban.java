package com.lat.servlets;

import com.lat.services.AdvertService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.lat.beans.Advert;

@WebServlet("/backoffice/report/advert/unban")
public class AdvertUnban extends HttpServlet
{
    private AdvertService advertService;

    public void init() throws ServletException
    {
        advertService = AdvertService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Advert advert = advertService.getAdvert(request);

        if (advert != null)
        {
            advertService.reauthorize(advert);
        }

        response.sendRedirect("/backoffice/suspended/adverts");
    }
}
