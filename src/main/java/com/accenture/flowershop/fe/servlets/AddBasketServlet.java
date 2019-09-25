package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.BasketBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.Order.Basket;
import com.accenture.flowershop.be.entity.Order.Order;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.BasketDTO;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "addBasketServlet", urlPatterns = "/addBasketServlet")
public class AddBasketServlet extends HttpServlet {
    @Autowired
    private OrderBusinessService orderBusinessService;

    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private BasketBusinessService basketBusinessService;
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

        if (session == null) {
            request.getRequestDispatcher("/indexServlet").forward(request, response);
        } else {
            UserDTO user = (UserDTO) session.getAttribute("user");
            Long flowerID = Long.parseLong(request.getParameter("flowerID"));
            Long quantityToBasket =Long.parseLong(request.getParameter("quantityToBasket"));
            Long quantityFlower = Long.parseLong(request.getParameter("quantity"));
            if (basketBusinessService.addBasket(user.getLogin(),flowerID,quantityToBasket,quantityFlower)) {
                session.setAttribute("flowers", flowerBusinessService.getAllFlower());
                session.setAttribute("basket",  basketBusinessService.getBasketByUserId(user.getId()));
                session.setAttribute("user", user);
                session.setAttribute("total",basketBusinessService.getTotal());
            }
            request.getRequestDispatcher("/WEB-INF/lib/userPage.jsp").forward(request, response);
        }
    }


    private void addBasketAttribute(Basket basket, HttpServletRequest request, UserDTO user) {

    }
}
