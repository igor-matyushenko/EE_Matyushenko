package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.fe.dto.OrderDTO;
import jdk.internal.module.ModuleLoaderMap;
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

@WebServlet(name = "closeOrderServlet", urlPatterns = "/closeOrderServlet")
public class CloseOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private Mapper mapper;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long idOrder = Long.parseLong(request.getParameter("orderId"));
        if(orderBusinessService.closeOrder(idOrder)){
            request.setAttribute("error","не закрылся заказ");
        }
        session.setAttribute("orderListAdmin", mapper.map(orderBusinessService.getAllOrders(), List.class));
        doGet(request,resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/lib/adminPage.jsp").forward(req, resp);
    }
}
