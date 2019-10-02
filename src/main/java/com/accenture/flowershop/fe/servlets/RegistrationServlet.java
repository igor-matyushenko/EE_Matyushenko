package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.UserDTO;

import org.dozer.Mapper;
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
import java.util.List;

@WebServlet(name = "registrationServlet", urlPatterns = "/registrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private  Mapper mapper;
    @Autowired
    private OrderBusinessService orderBusinessService;

    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/lib/registration.jsp").forward(req,resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login != null && password != null) {
            UserDTO user = new UserDTO(login, password);
            setParam(user,request);
            if (!userBusinessService.checkLogin(user.getLogin())) {
                User userEntity = mapper.map(user, User.class);
                user = mapper.map(userBusinessService.userRegistration(userEntity),UserDTO.class);
                log.debug("User успешно зарегистрирован : " + user.getLogin());
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(30 * 60);
                session.setAttribute("user", user);
                session.setAttribute("flowers", mapper.map(flowerBusinessService.getAllFlowers(), List.class));
                request.getRequestDispatcher("/WEB-INF/lib/userPage.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Пользователь существует  : " + user.getLogin());
                log.error("User существует  : " + user.getLogin());
                request.getRequestDispatcher("/WEB-INF/lib/registration.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Login or passwords введите снова");
            log.error("User не зарегистрирован : " + request.getAttribute("error"));
            request.getRequestDispatcher("/WEB-INF/lib/registration.jsp").forward(request, response);
        }

    }

    private void setParam(UserDTO user, HttpServletRequest request){
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setAddress(request.getParameter("address"));
        user.setEmail(request.getParameter("email"));
        user.setPhoneNumber(request.getParameter("phoneNumber"));
    }
}