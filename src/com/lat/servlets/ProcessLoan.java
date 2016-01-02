package com.lat.servlets;

import com.lat.beans.Apply;
import com.lat.beans.Loan;
import com.lat.forms.LoanForm;
import com.lat.services.AdvertService;
import com.lat.services.ApplyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loan/process")
public class ProcessLoan extends HttpServlet
{
    private ApplyService applyService;
    private AdvertService advertService;

    public void init() throws ServletException
    {
        applyService = ApplyService.getInstance();
        advertService = AdvertService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        advertService.processLoan(request);
        response.sendRedirect("/profil/loans");
    }
}
