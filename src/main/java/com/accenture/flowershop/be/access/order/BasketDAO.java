package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.Order.Basket;
import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface BasketDAO {

    List<Basket> getBasketByUserId(Long idUser);

    List<Basket> getBasketByOrderId(Long orderID);

    List<Basket> getActualBasketByUserId(Long idUser);

    Basket getBasketByFlowerName(String flowerName);

    boolean addBaskets(Basket basketList);

    boolean updateBasket(Basket basket);

    void updateBasketsList(List<Basket> basketList);
}
