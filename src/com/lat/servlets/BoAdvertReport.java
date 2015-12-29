package com.lat.servlets;

import com.lat.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/backoffice/report/adverts")
public class BoAdvertReport extends HttpServlet
{
    private UserService userService;

    public void init() throws ServletException
    {
        this.userService = UserService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String UserInSessionGroup = userService.getUserInSession().getGroup().getName();

        if(UserInSessionGroup != null && UserInSessionGroup.equals("ADMIN"))
        {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/backoffice/advertReport.jsp").forward(request, response);
        }
        else
        {
            response.sendRedirect("/home");
        }
    }
}
