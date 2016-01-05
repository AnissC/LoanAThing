package com.lat.servlets;

import com.lat.services.AdvertService;
import com.lat.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/backoffice/suspended/users")
public class SuspendedUsers extends HttpServlet
{
    private UserService userService;

    public void init() throws ServletException
    {
        userService = UserService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("suspendedUsers", this.userService.getAllSuspendedUsers());

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/backoffice/suspendedUsers.jsp").forward(request, response);
    }
}
