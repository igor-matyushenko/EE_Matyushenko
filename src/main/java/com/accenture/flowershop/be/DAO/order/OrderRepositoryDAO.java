package com.accenture.flowershop.be.DAO.order;

import com.accenture.flowershop.be.entity.order.OrderRepository;

import java.util.List;

public interface OrderRepositoryDAO {
    void saveOrder(OrderRepository order);

    void deleteOrder(OrderRepository order);

    void updateOrder(OrderRepository order);

    OrderRepository findOrder(Long orderId);

    List<OrderRepository> getAllMyOrders(Long idUser);

    List<OrderRepository> findAllOrders();
}
