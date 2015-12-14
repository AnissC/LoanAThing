package com.lat.servlets;

import com.lat.services.AdvertService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profil/adverts")
public class Adverts extends HttpServlet
{
    private AdvertService advertService;

    public void init() throws ServletException
    {
        this.advertService = AdvertService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("adverts", this.advertService.getAdvertsWithId(request));

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/profil/adverts.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("form", this.advertService.getAdvertForm());
        this.advertService.processApply(request);

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/profil/adverts.jsp").forward(request, response);
    }
}
