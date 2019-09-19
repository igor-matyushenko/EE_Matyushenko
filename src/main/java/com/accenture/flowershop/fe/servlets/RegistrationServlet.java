package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
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
import java.io.IOException;

@WebServlet(name = "registrationServlet", urlPatterns = "/registrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    private UserBusinessService userBusinessService;
    private static final Logger log = 	LoggerFactory.getLogger(LoginServlet.class);
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if(login != null && password != null){
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setFirstName(request.getParameter("FirstName"));
            user.setLastName(request.getParameter("LastName"));
            user.setAddress(request.getParameter("Address"));
            user.setPhoneNumber(request.getParameter("PhoneNumber"));
            if(!userBusinessService.checkLogin(user.getLogin())){
                log.debug("User успешно зарегистрирован : " + user.getLogin());
                userBusinessService.userRegistration(user);
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Пользователь существует  : " + user.getLogin());
                log.error("User существует  : " + user.getLogin());
                doGet(request,response);
            }
        } else {
            request.setAttribute("error", "Login or passwords введите снова");
            log.error("User не зарегистрирован : " + "заполните все поля");
            doGet(request,response);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }
}