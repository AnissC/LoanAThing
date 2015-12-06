package com.lat.servlets;

import com.lat.services.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends HttpServlet
{
    private UserService userService;

    public void init() throws ServletException {
        this.userService = UserService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.userService.logout();

        response.sendRedirect("http://localhost:8080/register");
    }
}