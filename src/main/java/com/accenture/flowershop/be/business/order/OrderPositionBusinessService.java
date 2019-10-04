package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.entity.Order.OrderPosition;

import java.math.BigDecimal;
import java.util.List;

public interface OrderPositionBusinessService {

    boolean addOrderPositionToBasket(Long userId, Long idFlower, Long quantityToOrderPosition, Long quantityFlower);




    List<OrderPosition> getActualBasketByUserId(Long userId);
}
