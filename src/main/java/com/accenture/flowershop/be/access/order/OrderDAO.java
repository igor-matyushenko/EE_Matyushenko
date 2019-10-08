package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.User;

import java.util.List;

public interface OrderDAO {

    Order getOrderByIdActualBasket(Long userId);

    Order addOrder(Order order);

    Order getOrderById(Long idOrder);

    List<Order> getAllOrders();

    List<Order> getOrdersByUser(User user);

    void updateOrder(Order order);
}
