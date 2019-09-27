package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.entity.Order.Order;
import com.accenture.flowershop.be.entity.user.User;

import java.util.List;

public interface OrderBusinessService {

    boolean closeOrder(Long idOrder);

    void addOrder(Order order);

    Order newOrderCreate(String loginUser);

    Order getOrderById(Long idOrder);

    List<Order> getAllOrders();

    List<Order> getOrdersByUserID(Long idUser);

    void updateOrder(Order order);
}
