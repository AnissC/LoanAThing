package com.lat.servlets;

import com.google.gson.Gson;
import com.lat.beans.Users;
import com.lat.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profil")
public class UserProfil extends HttpServlet
{
    private UserService userService;

    public void init() throws ServletException
    {
        this.userService = UserService.getInstance();

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        long idCurrentUser = Long.parseLong(request.getParameter("id"));
        Users user = this.userService.getUserById(idCurrentUser);
        request.setAttribute("User", user);
        return;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        Users user = userService.getUserById(Long.parseLong(request.getParameter("id")));
        String json = new Gson().toJson(user);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

}
