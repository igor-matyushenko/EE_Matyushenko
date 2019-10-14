package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.fe.dto.FlowerDTO;

import java.math.BigDecimal;
import java.util.List;

public interface FlowerBusinessService {

//    void increaseFlowersStockSize(int count);
//
    List<Flower> getAllFlowersBySearch(String flowerName, BigDecimal minPrice, BigDecimal maxPrice);

    List<Flower> getAllFlowers();

    void addFlower(Flower flower);

    void changeQuantityFlower(Long flowerId, long quantity);

    void deleteFlower(Flower flower);

    void updateFlower(Flower flower);

    Flower getFlowerById(Long idFlower);
}
