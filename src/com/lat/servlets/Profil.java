package com.lat.servlets;

import com.lat.beans.User;
import com.lat.services.AdvertService;
import com.lat.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profil")
public class Profil extends HttpServlet
{
    private UserService userService;
    private AdvertService advertService;

    public void init() throws ServletException
    {
        this.userService = UserService.getInstance();
        this.advertService = AdvertService.getInstance();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = userService.getUserInSession();

        request.setAttribute("user", user);
        int nbAdverts = 0;
        if(this.advertService.getAllAdverts() != null){
            nbAdverts = this.advertService.getAllAdverts().size();
        }
        request.setAttribute("nbAdverts", nbAdverts);

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/profil/profil.jsp").forward(request, response);
    }
}
