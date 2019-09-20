package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface FlowerDAO {
    List<Flower> getAllFlower();
    void addFlower(Flower flower);
    void deleteFlower(Flower flower);
    void editFlower(Flower flower);
    Flower getFlowerById(Long idFlower);
}
