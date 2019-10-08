package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.order.OrderDAO;
import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.StatusOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private OrderPositionBusinessService orderPositionBusinessService;

    private static final Logger LOG = LoggerFactory.getLogger(OrderBusinessServiceImpl.class);

    @Override
    public Order getOrderByIdActualBasket(Long userId) {
        return orderDAO.getOrderByIdActualBasket(userId);
    }

    @Override
    @Transactional
    public boolean closeOrder(Long orderId) {
        Order order = orderDAO.getOrderById(orderId);
        if (order == null) {
            return false;
        }
        order.setDateClose(Date.valueOf(LocalDate.now()));
        order.setStatusOrder(StatusOrder.CLOSED);
        orderDAO.updateOrder(order);
        return true;
    }

    @Override
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

    @Override
    @Transactional
    public boolean createOrder(Long userId) {
        User user = userBusinessService.findUserById(userId);
        Order order = orderDAO.getOrderByIdActualBasket(userId);
        order.setStatusOrder(StatusOrder.CREATED);
        orderDAO.updateOrder(order);
        userBusinessService.updateUser(user);
        return true;
    }

    @Override
    @Transactional
    public List<Order> getAllOrdersByUserId(Long userId) {
        User user = userBusinessService.findUserById(userId);
        List<Order> orders = new ArrayList<>();
        for (Order o : user.getOrderList()) {
            o.getBasketOrder().size();
            if(!StatusOrder.BASKET.equals(o.getStatusOrder())){
                orders.add(o);
            }
        }
        return orders;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderDAO.getOrderById(orderId);
    }

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        for (User u : userDAO.getUserList()) {
            u.getOrderList().size();
            for (Order o : u.getOrderList()) {
                o.getBasketOrder().size();
            }
        }
        return orderDAO.getAllOrders();
    }


    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }


}
