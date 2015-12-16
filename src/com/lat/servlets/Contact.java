package com.lat.servlets;

import com.lat.beans.User;
import com.lat.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/contact")
public class Contact extends HttpServlet
{
    private UserService userService;

    public void init() throws ServletException
    {
        this.userService = UserService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = userService.getUserInSession();

        request.setAttribute("user", user);

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/profil/inbox.jsp").forward(request, response);
    }
}
