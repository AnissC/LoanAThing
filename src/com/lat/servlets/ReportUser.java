package com.lat.servlets;

import com.lat.beans.User;
import com.lat.services.ReportingUserService;
import com.lat.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/report")
public class ReportUser extends HttpServlet
{
    private UserService userService;
    private ReportingUserService reportingUserService;

    public void init() throws ServletException
    {
        this.userService = UserService.getInstance();
        this.reportingUserService = ReportingUserService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = userService.getUserById(Integer.valueOf(request.getParameter("userId")));
        if(user != null){
            reportingUserService.reportUser(user);
        }
        /*TODO voir si il est possible de juste envoyer un toaster sans rediriger*/
        response.sendRedirect("/home");
    }
}
