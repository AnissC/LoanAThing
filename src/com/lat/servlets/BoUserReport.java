package com.lat.servlets;

import com.lat.beans.*;
import com.lat.beans.Advert;
import com.lat.dao.ReportingUserDao;
import com.lat.services.ReportingUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/backoffice/report/users")
public class BoUserReport extends HttpServlet
{
    private ReportingUserService reportingUserService;

    public void init() throws ServletException
    {
        this.reportingUserService = ReportingUserService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<ReportingUser> reportingUsers = reportingUserService.getAllReportingUsers();
        ArrayList<User> users = new ArrayList<>();

        if (reportingUsers != null)
        {
            for (ReportingUser reportingUser : reportingUsers) {
                users.add(reportingUser.getUser());
            }
        }

        request.setAttribute("reportedUsers", users);

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/backoffice/userReport.jsp").forward(request, response);
    }
}

