package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.fe.dto.FlowerDTO;

import java.util.List;

public interface FlowerBusinessService {
    List<Flower> getAllFlower();
    void addFlower(Flower flower);
    void deleteFlower(Flower flower);
    void editFlower(Flower flower);
    Flower getFlowerById(Long idFlower);
}
