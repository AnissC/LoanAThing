package com.lat.servlets;

import com.google.gson.Gson;
import com.lat.beans.User;
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

    public void init() throws ServletException
    {
        this.userService = UserService.getInstance();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = userService.getUserInSession();

        /* TODO : Nico */
        String json = new Gson().toJson(user);

        response.getWriter().write(json);

        //request.setAttribute("user", user);

        //this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Integer idCurrentUser = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(idCurrentUser);
        request.setAttribute("User", user);
        return;
    }
}
