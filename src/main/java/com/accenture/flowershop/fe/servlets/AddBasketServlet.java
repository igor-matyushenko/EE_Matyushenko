package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.ObjectMapperUtils;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.OrderPositionBusinessService;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.OrderPositionDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.dozer.Mapper;
import org.hibernate.mapping.Collection;
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

@WebServlet(name = "addBasketServlet", urlPatterns = "/addBasketServlet")
public class AddBasketServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private OrderPositionBusinessService orderPositionBusinessService;
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
        if (session == null) {
            request.getRequestDispatcher("/indexServlet").forward(request, response);
        } else {
            UserDTO user = (UserDTO) session.getAttribute("user");
            Long flowerID = Long.parseLong(request.getParameter("flowerID"));
            Long quantityToOrderPos =Long.parseLong(request.getParameter("quantityToPos"));
            Long quantityFlower = Long.parseLong(request.getParameter("quantity"));
            if (orderPositionBusinessService.addOrderPositionToBasket(user.getId(),flowerID,quantityToOrderPos,quantityFlower)) {

                session.setAttribute("flowers", mapperUtils.mapList(flowerBusinessService.getAllFlowers(), FlowerDTO.class));
                session.setAttribute("basket",  mapperUtils.mapList(orderPositionBusinessService.getActualBasketByUserId(user.getId()), OrderPositionDTO.class));
                session.setAttribute("user", user);
            }
            request.getRequestDispatcher("/WEB-INF/lib/userPage.jsp").forward(request, response);
        }
    }
}
