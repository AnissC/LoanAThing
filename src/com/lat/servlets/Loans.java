package com.lat.servlets;

import com.lat.beans.*;
import com.lat.services.AdvertService;
import com.lat.services.ApplyService;
import com.lat.services.LoanService;
import com.lat.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profil/loans")
public class Loans extends HttpServlet
{
    private LoanService loanService;
    private UserService userService;

    public void init() throws ServletException
    {
        loanService = LoanService.getInstance();
        userService = UserService.getInstance();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = userService.getUserInSession();
        request.setAttribute("loansAsked", loanService.getAllLoansAskedByUser(user));
        request.setAttribute("loansOffered", loanService.getAllLoansOfferedByUser(user));

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/profil/loans.jsp").forward(request, response);
    }
}
