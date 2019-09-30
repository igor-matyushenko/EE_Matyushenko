package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.entity.Order.OrderPosition;

import java.math.BigDecimal;
import java.util.List;

public interface OrderPositionBusinessService {

    List<OrderPosition> getOrderPositionByUserId(Long idUser);

    List<OrderPosition> getNewOrderPositionByUserId(Long idUser);

    Boolean addOrderPosition(String userLogin, Long idFlower, Long quantityToOrderPosition, Long quantityFlower);

    List<OrderPosition> getOrderPositionByOrderId(Long idOrder);

    void updateOrderPositionList(List<OrderPosition> orderPositionList);

    BigDecimal getTotalSumFromActualBasket();

    void setTotalSumFromActualBasket(BigDecimal totalSumFromActualBasketSum);
}
