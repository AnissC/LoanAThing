package com.lat.filters;

import com.lat.beans.User;
import com.lat.services.UserService;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/backoffice/*")
public class AdminFilter implements Filter
{
    private UserService userService;

    public void init(FilterConfig config) throws ServletException
    {
        this.userService = UserService.getInstance();
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String path = request.getRequestURI().substring(request.getContextPath().length());
        if (path.startsWith("/inc")) {
            chain.doFilter(request, response);
            return;
        }

        User user = userService.getUserInSession();

        if (user.getGroup().getName() != null && user.getGroup().getName().equals("ADMIN")) {
            if (path.startsWith("/backoffice")) {
                chain.doFilter(request, response);
            }
        } else {
            if (path.startsWith("/backoffice")) {
                request.getRequestDispatcher("/home").forward(request, response);
            }
            chain.doFilter(request, response);
        }
    }

    public void destroy()
    {
    }
}
