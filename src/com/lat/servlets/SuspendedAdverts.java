package com.lat.servlets;

import com.lat.beans.*;
import com.lat.services.AdvertService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/backoffice/suspended/adverts")
public class SuspendedAdverts extends HttpServlet {
    private AdvertService advertService;

    public void init() throws ServletException
    {
        advertService = AdvertService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("suspendedAdverts", this.advertService.getAllSuspendedAdverts());

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/backoffice/suspendedAdverts.jsp").forward(request, response);
    }
}
