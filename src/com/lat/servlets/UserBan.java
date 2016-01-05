package com.lat.servlets;

import com.lat.beans.Advert;
import com.lat.beans.User;
import com.lat.services.AdvertService;
import com.lat.services.ReportingAdvertService;
import com.lat.services.ReportingUserService;
import com.lat.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/backoffice/report/user/ban")
public class UserBan extends HttpServlet
{
    private UserService userService;
    private ReportingUserService reportingUserService;

    public void init() throws ServletException
    {
        userService = UserService.getInstance();
        reportingUserService = ReportingUserService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = userService.getUserById(Integer.valueOf(request.getParameter("userId")));

        if (user != null)
        {
            userService.ban(user);
            reportingUserService.ignore(user);
        }
    }
}
