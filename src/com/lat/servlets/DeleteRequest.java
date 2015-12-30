package com.lat.servlets;

import com.lat.beans.Apply;
import com.lat.services.ApplyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/request/delete")
public class DeleteRequest extends HttpServlet{

    private ApplyService applyService;

    public void init() throws ServletException
    {
        this.applyService = ApplyService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Apply apply = applyService.getApplyById(Long.valueOf(request.getParameter("id")));

        if (apply != null){
            applyService.delete(apply);
        }
    }
}
