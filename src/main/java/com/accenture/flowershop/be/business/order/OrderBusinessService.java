package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.entity.order.Order;

import java.util.List;

public interface OrderBusinessService {

    Order getOrderByIdActualBasket(Long userId);

    boolean closeOrder(Long idOrder);

    void addOrder(Order order);

    boolean createOrder(Long userId);

    Order getOrderById(Long orderId);

    List<Order> getAllOrders();

    List<Order> getAllOrdersByUserId(Long userId);

    void updateOrder(Order order);
}
