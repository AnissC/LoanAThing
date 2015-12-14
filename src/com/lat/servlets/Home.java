package com.lat.servlets;

import com.lat.beans.Advert;
import com.lat.services.AdvertService;
import com.lat.services.ApplyService;
import com.lat.services.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/home")
public class Home extends HttpServlet
{
    private AdvertService advertService;
    private ApplyService applyService;
    private CategoryService categoryService;

    public void init() throws ServletException
    {
        this.advertService = AdvertService.getInstance();
        this.categoryService = CategoryService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int nbAdverts = 0;
        if(this.advertService.getAllAdverts() != null){
            nbAdverts = this.advertService.getAllAdverts().size();
        }
        request.setAttribute("nbAdverts", nbAdverts);
        request.setAttribute("adverts", this.advertService.getAllAdverts());
        request.setAttribute("categories", this.categoryService.getAllCategory());

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/advert/home.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }
}
