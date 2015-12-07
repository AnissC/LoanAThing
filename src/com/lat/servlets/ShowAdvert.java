package com.lat.servlets;

import com.lat.beans.Advert;
import com.lat.services.AdvertService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowAdvert extends HttpServlet
{
    private AdvertService advertService;

    public void init() throws ServletException
    {
        this.advertService = AdvertService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("advert", this.advertService.getAdvert(id));

        this.getServletContext().getRequestDispatcher("/WEB-INF/showAdvert.jsp").forward(request, response);
    }
}
