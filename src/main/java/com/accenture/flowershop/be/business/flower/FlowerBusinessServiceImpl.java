package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.access.flower.FlowerDAO;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService{

    @Autowired
    private FlowerDAO flowerDAO;

    @Override
    public List<Flower> getAllFlower(){
        return flowerDAO.getAllFlower();
    }

    @Override
    public void addFlower(Flower flower) {
        flowerDAO.addFlower(flower);
    }

    @Override
    public void deleteFlower(Flower flower) {
        flowerDAO.deleteFlower(flower);
    }

    @Override
    public void editFlower(Flower flower) {
        flowerDAO.editFlower(flower);
    }

    @Override
    public Flower getFlowerById(Long idFlower) {
        return flowerDAO.getFlowerById(idFlower);
    }
}
