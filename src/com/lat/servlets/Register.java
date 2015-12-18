package com.lat.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lat.services.UserService;

@WebServlet("/register")
public class Register extends HttpServlet
{
    private UserService userService;

    public void init() throws ServletException
    {
        this.userService = UserService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/connect/register.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("form", this.userService.getUserForm());
        request.setAttribute("user", this.userService.processUser(request));

        /* TODO : Find a better way to redirect the user */
        if (request.getAttribute("user") == null) {
            response.sendRedirect("/register");
        } else {
            response.sendRedirect("/login");
        }
    }
}
