package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.fe.dto.FlowerDTO;

import java.math.BigDecimal;
import java.util.List;

public interface FlowerBusinessService {

    List<Flower> getAllFlowers();

    List<Flower> getAllFlowersBySearch(String flowerName, BigDecimal minPrice, BigDecimal maxPrice);

    void addFlower(Flower flower);

    void deleteFlower(Flower flower);

    void updateFlower(Flower flower);

    Flower getFlowerById(Long idFlower);
}
