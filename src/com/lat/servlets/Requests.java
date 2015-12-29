package com.lat.servlets;

import com.lat.beans.Apply;
import com.lat.services.AdvertService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profil/requests")
public class Requests extends HttpServlet
{
    private AdvertService advertService;

    public void init() throws ServletException
    {
        this.advertService = AdvertService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<List<Apply>> requests = this.advertService.getPendingRequests(request) ;
        request.setAttribute("pendingRequests", this.advertService.getPendingRequests(request));

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/profil/requests.jsp").forward(request, response);
    }
}
