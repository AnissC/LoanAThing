package com.lat.servlets;

import com.lat.services.AdvertService;
import com.lat.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/advert/view")
public class Advert extends HttpServlet
{
    private AdvertService advertService;
    private UserService userService;

    public void init() throws ServletException
    {
        this.advertService = AdvertService.getInstance();
        this.userService = UserService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("advert", this.advertService.getAdvert(request));
        request.setAttribute("categories", this.advertService.getCategories());
        request.setAttribute("user", this.userService.getUserInSession());

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/advert/advert.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.advertService.updateAdvert(request);

        response.sendRedirect("/advert/view?id=" + request.getParameter("id"));
    }
}
