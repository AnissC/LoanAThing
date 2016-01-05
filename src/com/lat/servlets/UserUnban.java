package com.lat.servlets;

import com.lat.beans.Advert;
import com.lat.beans.User;
import com.lat.services.AdvertService;
import com.lat.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/backoffice/report/user/unban")
public class UserUnban extends HttpServlet
{
    private UserService userService;

    public void init() throws ServletException
    {
        userService = UserService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = userService.getUserById(Integer.valueOf(request.getParameter("userId")));

        if (user != null)
        {
            userService.reauthorize(user);
        }
    }
}
