package com.lat.servlets;

import com.lat.beans.Advert;
import com.lat.dao.AdvertDao;
import com.lat.dao.DAOFactory;
import com.lat.forms.AdvertAddForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ListAdverts extends HttpServlet
{
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_ADVERTS = "adverts";
    public static final String VUE = "/WEB-INF/adverts.jsp";

    private AdvertDao advertDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Advert */
        this.advertDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getAdvertDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ArrayList adverts = this.advertDao.find();

        request.setAttribute(ATT_ADVERTS, adverts);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
