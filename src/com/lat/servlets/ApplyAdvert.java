package com.lat.servlets;

import com.lat.beans.Apply;
import com.lat.services.AdvertService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/advert/apply")
public class ApplyAdvert extends HttpServlet
{
    private AdvertService advertService;

    public void init() throws ServletException
    {
        this.advertService = AdvertService.getInstance();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Apply apply = this.advertService.processApply(request);

        response.sendRedirect("/advert/view?id=" + apply.getAdvert().getId());
    }
}
