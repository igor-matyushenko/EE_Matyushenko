package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.Order.Basket;
import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface BasketDAO {
    List<Basket> getBasketByUserId(Long idUser);
    List<Basket> getNewBasketByUserId(Long idUser);
    Basket getBasketByFlowerName(String flowerName);
    Boolean addBasket(Basket basketList);
    List<Basket> getBasketByOrderId(Long orderID);
    Boolean updateBasket(Basket basket);
    void editBasket(List<Basket> basketList);
}
