package com.accenture.flowershop.fe.servlets;


import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "logoutServlet", urlPatterns = "/logoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/lib/login.jsp").forward(request, response);
    }
}
