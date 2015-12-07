package com.lat.servlets;

import com.lat.services.AdvertService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAdvert extends HttpServlet
{
    private AdvertService advertService;

    public void init() throws ServletException
    {
        this.advertService = AdvertService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.getServletContext().getRequestDispatcher("/WEB-INF/addAdvert.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("form", this.advertService.getAdvertForm());
        request.setAttribute("advert", this.advertService.processAdvert(request));

        this.getServletContext().getRequestDispatcher("/WEB-INF/addAdvert.jsp").forward(request, response);
    }
}
