package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.Order.OrderPosition;

import java.util.List;

public interface OrderPositionDAO {



    List<OrderPosition> getOrderPositionByUser(Long userId);

    List<OrderPosition> getActualOrderPositionByUserId(Long idUser, Long idOrder);

    List<OrderPosition> getOrderPositionByOrderId(Long orderID);

    boolean addOrderPosition(OrderPosition orderPositionList);

    boolean updateOrderPosition(OrderPosition orderPosition);

    void updateOrderPositionList(List<OrderPosition> orderPositionList);
}
