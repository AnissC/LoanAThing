package com.lat.servlets;

import com.lat.services.AdvertService;
import com.lat.services.LoanService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/backoffice/stats")
public class BoStats extends HttpServlet
{
    private LoanService loanService;
    private AdvertService advertService;

    public void init() throws ServletException
    {
        loanService = LoanService.getInstance();
        advertService = AdvertService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/backoffice/stats.jsp").forward(request, response);
    }
}

