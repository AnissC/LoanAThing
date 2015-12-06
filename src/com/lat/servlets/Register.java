package com.lat.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lat.services.UserService;

public class Register extends HttpServlet
{
    private UserService userService;

    public void init() throws ServletException {
        this.userService = UserService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("form", this.userService.getRegisterForm());
        request.setAttribute("user", this.userService.processUser(request));

        this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }
}
