package com.lat.servlets;

import com.lat.services.LoanService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loan/submit/return-code")
public class LoanSubmitReturnCode extends HttpServlet{

    private LoanService loanService;

    public void init() throws ServletException
    {
        this.loanService = LoanService.getInstance();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String code = request.getParameter("code");
        loanService.submitReturnCode(Integer.valueOf(request.getParameter("loanId")), code);
        response.sendRedirect("/profils/loans");
    }
}
