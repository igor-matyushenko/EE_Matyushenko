package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.entity.Order.Basket;

import java.math.BigDecimal;
import java.util.List;

public interface BasketBusinessService {

    List<Basket> getBasketsByUserId(Long idUser);

    List<Basket> getNewBasketByUserId(Long idUser);

    Boolean addBasket(String userLogin, Long idFlower, Long quantityToBasket, Long quantityFlower);

    List<Basket> getBasketByOrderId(Long idOrder);

    void updateBasketList(List<Basket> basketList);

    BigDecimal getTotalSumFromActualBasket();

    void setTotalSumFromActualBasket(BigDecimal totalSumFromActualBasketSum);
}
