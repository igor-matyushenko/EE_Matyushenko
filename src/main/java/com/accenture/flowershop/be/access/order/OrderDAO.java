package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.Order.Order;
import com.accenture.flowershop.be.entity.user.User;

import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);
    Order getOrderById(Long idOrder);
    List<Order> getAllOrder();
    List<Order> getOrderByUserID(Long userId);
    void editOrder(Order order);
}
