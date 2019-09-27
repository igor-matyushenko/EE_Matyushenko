package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.math.BigDecimal;
import java.util.List;

public interface FlowerDAO {

    List<Flower> getAllFlowers();

    void addFlower(Flower flower);

    void deleteFlower(Flower flower);

    void updateFlower(Flower flower);

    Flower getFlowerById(Long idFlower);

    List<Flower> getAllFlowersBySearch(String flowerName, BigDecimal minPrice, BigDecimal maxPrice);
}
