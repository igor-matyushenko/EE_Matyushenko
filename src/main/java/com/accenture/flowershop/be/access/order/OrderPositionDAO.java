package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.Order.OrderPosition;

import java.util.List;

public interface OrderPositionDAO {

    List<OrderPosition> getOrderPositionByUserId(Long idUser);

    List<OrderPosition> getOrderPositionByOrderId(Long orderID);

    List<OrderPosition> getActualOrderPositionByUserId(Long idUser);

    OrderPosition getOrderPositionByFlowerName(String flowerName);

    boolean addOrderPosition(OrderPosition orderPositionList);

    boolean updateOrderPosition(OrderPosition orderPosition);

    void updateOrderPositionList(List<OrderPosition> orderPositionList);
}
