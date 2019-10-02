package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
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

@WebServlet(name = "payCreateOrderServlet", urlPatterns = "/payCreateOrderServlet")
public class PayCreateOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
            UserDTO user = (UserDTO) session.getAttribute("user");
            Long orderId = Long.parseLong(request.getParameter("orderListPayId"));
            if (!userBusinessService.payOrder(user.getId(),orderId)){
                request.setAttribute("orderMessage", "Не хватает денежных средств!");
            } else {
                User userEntity = userBusinessService.findUserById(user.getId());
                user = mapper.map(userEntity,UserDTO.class);
                session.setAttribute("user", user);
                session.setAttribute("orderList", mapper.map(orderBusinessService.getAllOrdersByUserId(user.getId()), List.class));
            }
            request.getRequestDispatcher("/WEB-INF/lib/userPage.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/loginServlet").forward(request, response);
        }
    }
}
