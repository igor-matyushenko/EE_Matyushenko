package com.accenture.flowershop.fe.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( name = "indexServlet",urlPatterns = "/indexServlet")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false)!=null){
            request.getSession().invalidate();
        }

        String path = "/WEB-INF/lib/login.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }

}
