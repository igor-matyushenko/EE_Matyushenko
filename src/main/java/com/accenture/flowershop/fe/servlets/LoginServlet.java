package com.accenture.flowershop.fe.servlets;


import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "loginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserBusinessService userBusinessService;

    private static final Logger log = 	LoggerFactory.getLogger(LoginServlet.class);


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

            if (userBusinessService.userVerification(login, password) != null) {
                User user = userBusinessService.userVerification(login, password);
                if(user.getRole().equals(Roles.ADMIN)){
                    request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
                    session.setAttribute(user.toString(), user);
                } else if(user.getRole().equals(Roles.USER)){
                    request.getRequestDispatcher("/userPage.jsp").forward(request, response);
                    session.setAttribute(user.toString(), user);
                } else {
                    request.setAttribute("error", "Invalid ROLE");
                    doGet(request,response);
                }
            } else{
            request.setAttribute("error", "Invalid login or password");
            doGet(request,response);
            }






    }
}
