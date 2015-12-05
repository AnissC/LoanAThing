package com.lat.servlets;

import com.lat.beans.Advert;
import com.lat.dao.AdvertDao;
import com.lat.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowAdvert extends HttpServlet
{
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_ADVERT = "advert";
    public static final String VUE = "/WEB-INF/showAdvert.jsp";

    private AdvertDao advertDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Advert */
        this.advertDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getAdvertDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Advert advert = this.advertDao.findOneById(id);

        request.setAttribute(ATT_ADVERT, advert);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
