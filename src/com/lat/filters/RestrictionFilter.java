package com.lat.filters;

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


@WebFilter("/*")
public class RestrictionFilter implements Filter
{
    public void init(FilterConfig config) throws ServletException
    {
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

        HttpSession session = request.getSession();

        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        if (session.getAttribute("userSession") == null) {
            if (path.startsWith("/register") || path.startsWith("/login")) {
                chain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("/welcome").forward(request, response);
            }
        } else {
            if (path.startsWith("/login") || path.startsWith("/register")) {
                request.getRequestDispatcher("/home").forward(request, response);
            }
            chain.doFilter(request, response);
        }
    }

    public void destroy()
    {
    }
}
