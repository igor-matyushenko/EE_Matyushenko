package com.accenture.flowershop.fe.servlets;


import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.user.UserDTO;
import com.accenture.flowershop.fe.enums.Roles;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapping;
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
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "loginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserBusinessService userBusinessService;

    private DozerBeanMapper mapper;
    private List<String> list;

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
        UserDTO user = new UserDTO();
        user.setLogin(login);
        user.setPassword(password);
            if (userBusinessService.userVerification(user.getLogin(), user.getPassword()) != null) {
                    list = new ArrayList<>();
                    list.add("dozer_mapping.xml");
                    mapper = new DozerBeanMapper(list);
                User userEntity = userBusinessService.userVerification(user.getLogin(), user.getPassword());
                user = mapper.map(userEntity,UserDTO.class);

                if(user.getRole().equals(Roles.ADMIN)){
                    request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
                    session.setAttribute(user.toString(), user);
                }
                if(user.getRole().equals(Roles.USER)){
                    request.getRequestDispatcher("/userPage.jsp").forward(request, response);
                    session.setAttribute(user.toString(), user);
                }
            } else{
            request.setAttribute("error", "Invalid login or password");
            doGet(request,response);
            }






    }
}
