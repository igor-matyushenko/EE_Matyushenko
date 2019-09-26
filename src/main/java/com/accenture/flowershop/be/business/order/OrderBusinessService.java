package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.entity.Order.Order;
import com.accenture.flowershop.be.entity.user.User;

import java.util.List;

public interface OrderBusinessService {
    Boolean closeOrder(Long orderId);
    void addOrder(Order order);
    Order newOrderCreate(String  userLogin);
    Order getOrderById(Long idOrder);
    List<Order> getAllOrder();
    List<Order> getOrderByUserID(Long idUser);
    void editOrder(Order order);
}
