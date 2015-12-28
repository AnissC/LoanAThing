package com.lat.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by Nico on 28/12/2015.
 */

@WebServlet("/backoffice")
public class BoHome extends HttpServlet{

    public void init() throws ServletException
    {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/backoffice/home.jsp").forward(request, response);
    }
}
