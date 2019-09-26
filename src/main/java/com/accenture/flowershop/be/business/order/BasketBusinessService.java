package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.entity.Order.Basket;

import java.math.BigDecimal;
import java.util.List;

public interface BasketBusinessService {
    List<Basket> getBasketByUserId(Long idUser);
    List<Basket> getNewBasketByUserId(Long idUser);
    Boolean addBasket(String userLogin, Long flowerID, Long quantityToBasket, Long quantityFlower );
    List<Basket> getBasketByOrderId(Long orderID);
    void editBasket(List<Basket> basketList);
    BigDecimal getTotal();
    void setTotalSum(BigDecimal bigDecimal);
}
