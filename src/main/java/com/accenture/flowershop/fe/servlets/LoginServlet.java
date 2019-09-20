package com.accenture.flowershop.fe.servlets;


import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.enums.Roles;
import org.dozer.DozerBeanMapper;
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

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    private DozerBeanMapper mapper;
    private List<String> list;

    private static final Logger log = 	LoggerFactory.getLogger(LoginServlet.class);


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/lib/login.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserDTO user = new UserDTO();
        user.setLogin(login);
        user.setPassword(password);
            if (userBusinessService.userVerification(user.getLogin(), user.getPassword()) != null) {
                    list = new ArrayList<>();
                    list.add("dozer_mapping.xml");
                    mapper = new DozerBeanMapper(list);
                User userEntity = userBusinessService.userVerification(user.getLogin(), user.getPassword());
                user = mapper.map(userEntity,UserDTO.class);
                HttpSession session=request.getSession();
                session.setMaxInactiveInterval(30*60);
                if(user.getRole().equals(Roles.ADMIN)){
                    session.setAttribute("user",user);
                    request.getRequestDispatcher("/WEB-INF/lib/adminPage.jsp").forward(request, response);
                }
                if(user.getRole().equals(Roles.USER)){
                    session.setAttribute("user",user);
                    session.setAttribute("flowers",flowerBusinessService.getAllFlower());
                    request.getRequestDispatcher("/WEB-INF/lib/userPage.jsp").forward(request, response);

                }
            } else{
            request.setAttribute("error", "Invalid login or password");
            doGet(request,response);
            }






    }
}
