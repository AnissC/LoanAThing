package com.lat.servlets;

import com.lat.beans.*;
import com.lat.services.ReportingAdvertService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.lat.beans.Advert;

@WebServlet("/backoffice/report/adverts")
public class BoAdvertReport extends HttpServlet
{
    private ReportingAdvertService reportingAdvertService;

    public void init() throws ServletException
    {
        this.reportingAdvertService = ReportingAdvertService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<ReportingAdvert> reportingAdverts = reportingAdvertService.getAllReportingAdverts();
        ArrayList<Advert> adverts = new ArrayList<>();

        if (reportingAdverts != null)
        {
            for (ReportingAdvert reportingAdvert : reportingAdverts) {
                adverts.add(reportingAdvert.getAdvert());
            }
        }

        request.setAttribute("reportedAdverts", adverts);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/backoffice/advertReport.jsp").forward(request, response);
    }
}
