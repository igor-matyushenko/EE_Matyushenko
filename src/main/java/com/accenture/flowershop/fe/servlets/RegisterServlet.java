package com.accenture.flowershop.fe.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/registerServlet")
public class RegisterServlet extends HttpServlet {

    @Autowired
    private UserBusinessService userBusinessService;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");



        if (userBusinessService.createNewUser(login,password)) {
            req.getRequestDispatcher("/userInfo.jsp").forward(req, resp);
        } else {
            req.setAttribute("error","This login have in system!\n"+
                    "Please choose other login");
            req.getRequestDispatcher("/registration.jsp").forward(req,resp);
        }
    }


}

