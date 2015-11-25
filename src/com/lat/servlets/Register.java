package com.lat.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lat.beans.User;
import com.lat.dao.DAOFactory;
import com.lat.dao.UserDao;
import com.lat.forms.RegisterForm;

public class Register extends HttpServlet
{
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_USER = "user";
    public static final String ATT_FORM = "form";
    public static final String VUE = "/WEB-INF/register.jsp";

    private UserDao userDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.userDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        /* Préparation de l'objet formulaire */
        RegisterForm form = new RegisterForm(userDao);

        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        User user = form.processUser(request);

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute(ATT_FORM, form);
        request.setAttribute(ATT_USER, user);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
