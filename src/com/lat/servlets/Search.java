package com.lat.servlets;

import com.google.gson.Gson;
import com.lat.beans.Advert;
import com.lat.services.AdvertService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/search")
public class Search extends HttpServlet {
    private AdvertService advertService;

    public void init() throws ServletException
    {
        this.advertService = AdvertService.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String search = request.getParameter("name");
        List<Advert> adverts;

        if (search == ""){
            adverts = advertService.getAllAdverts();
        }
        else{
            adverts = advertService.getAdvertsWithName(search);
        }
        String jsonResponse = new Gson().toJson(adverts);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}

