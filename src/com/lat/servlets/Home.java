package com.lat.servlets;

import com.lat.services.AdvertService;
import com.lat.services.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class Home extends HttpServlet
{
    private AdvertService advertService;
    private CategoryService categoryService;

    public void init() throws ServletException
    {
        this.advertService = AdvertService.getInstance();
        this.categoryService = CategoryService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("adverts", this.advertService.getAllAdverts());
        request.setAttribute("categories", this.categoryService.getAllCategory());

        this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }
}
