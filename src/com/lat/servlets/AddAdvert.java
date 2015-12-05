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

public class AddAdvert extends HttpServlet
{
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_ADVERT = "advert";
    public static final String ATT_FORM = "form";
    public static final String VUE = "/WEB-INF/addAdvert.jsp";

    private AdvertDao advertDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Advert */
        this.advertDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getAdvertDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        /* Affichage de la page de création d'une offre de prêt */
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        /* Préparation de l'objet formulaire */
        AdvertAddForm form = new AdvertAddForm(advertDao);

        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Advert advert = form.processAdvert(request);

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute(ATT_FORM, form);
        request.setAttribute(ATT_ADVERT, advert);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
