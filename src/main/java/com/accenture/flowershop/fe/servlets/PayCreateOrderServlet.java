package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.Order.Order;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.dozer.Mapper;
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

@WebServlet(name = "payCreateOrderServlet", urlPatterns = "/payCreateOrderServlet")
public class PayCreateOrderServlet extends HttpServlet {

    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private Mapper mapper;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            String login = ((UserDTO) session.getAttribute("user")).getLogin();
            Long orderId = ((Order) session.getAttribute("order")).getId();
            if (!userBusinessService.payCreatedOrder(login,orderId)){
                request.setAttribute("orderMessage", "Заказ не создан!");
            } else {
                User userEntity = userBusinessService.findUserByLogin(login);
                UserDTO user = mapper.map(userEntity,UserDTO.class);
                session.setAttribute("user", user);
                session.setAttribute("order", null);
                session.setAttribute("orderList",orderBusinessService.getOrderByUserID(user.getId()));
            }
            request.getRequestDispatcher("/WEB-INF/lib/userPage.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/loginServlet").forward(request, response);
        }
    }
}
