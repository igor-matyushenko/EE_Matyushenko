package com.accenture.flowershop.fe.servlets;


import com.accenture.flowershop.be.business.ObjectMapperUtils;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.order.OrderPositionBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.order.OrderPosition;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.OrderPositionDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
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
    @Autowired
    private OrderPositionBusinessService orderPositionBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private OrderBusinessService orderBusinessService;

    @Autowired
    private ObjectMapperUtils mapperUtils;


    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserDTO user = new UserDTO();
        user.setLogin(login);
        user.setPassword(password);
        User userEntity = userBusinessService.userVerification(user.getLogin(), user.getPassword());
        if (userEntity != null) {
            user = mapperUtils.map(userEntity, UserDTO.class);
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(30 * 60);
            if (Roles.ADMIN.equals(user.getRole())) {
                session.setAttribute("user", user);
                session.setAttribute("orderListAdmin", mapperUtils.mapList(orderBusinessService.getAllOrders(),  OrderDTO.class));
                request.getRequestDispatcher("/WEB-INF/lib/adminPage.jsp").forward(request, response);
            }
            if (user.getRole().equals(Roles.USER)) {
                session.setAttribute("user", user);
                session.setAttribute("flowers", mapperUtils.mapList(flowerBusinessService.getAllFlowers(), FlowerDTO.class));
                session.setAttribute("orderList", user.getOrderList());
                session.setAttribute("basket", mapperUtils.mapList(orderPositionBusinessService.getActualBasketByUserId(user.getId()), OrderPositionDTO.class));
                request.getRequestDispatcher("/WEB-INF/lib/userPage.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Invalid login or password");
            request.getRequestDispatcher("/WEB-INF/lib/login.jsp").forward(request, response);
        }


    }
}
