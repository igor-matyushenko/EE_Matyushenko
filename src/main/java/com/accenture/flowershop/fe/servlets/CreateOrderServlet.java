package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.ObjectMapperUtils;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;

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
import java.util.List;

@WebServlet(name = "createOrderServlet", urlPatterns = "/createOrderServlet")
public class CreateOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private ObjectMapperUtils mapperUtils;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserDTO user = (UserDTO) session.getAttribute("user");
            if (!orderBusinessService.createOrder(user.getId())) {
                request.setAttribute("orderMessage", "Ошибка создания заказа!");
            } else {
                session.setAttribute("user", user);
                session.setAttribute("basket", null);
                session.setAttribute("orderList", mapperUtils.mapList(orderBusinessService.getAllOrdersByUserId(user.getId()), OrderDTO.class));
            }
            request.getRequestDispatcher("/WEB-INF/lib/userPage.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/loginServlet").forward(request, response);
        }
    }
}
