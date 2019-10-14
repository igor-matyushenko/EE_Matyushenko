package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.access.flower.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {

    @Autowired
    private FlowerRepository flowerRepository;


//    @Override
//    public void increaseFlowersStockSize(int count) {
//        flowerRepository.increaseFlowersStockSize(count);
//    }
//
    @Override
    public List<Flower> getAllFlowersBySearch(String flowerName, BigDecimal minPrice, BigDecimal maxPrice) {
        if (maxPrice == null) {
            maxPrice = new BigDecimal(Long.MAX_VALUE);
        }
        if (minPrice == null) {
            minPrice = BigDecimal.ZERO;
        }
        if (flowerName == null || flowerName.equals("")) {
            List<Flower> flowerListBySearch = new ArrayList<>();
            for (Flower flower : getAllFlowers()) {
                int minP = flower.getPriceFlower().compareTo(minPrice);
                int maxP = flower.getPriceFlower().compareTo(maxPrice);
                if (maxP <= 0 && minP >= 0) {
                    flowerListBySearch.add(flower);
                }
            }
            return flowerListBySearch;
        }
        return flowerRepository.getAllFlowersBySearch(flowerName.toUpperCase(), minPrice, maxPrice);
    }

    @Override
    public List<Flower> getAllFlowers() {
        return flowerRepository.findAll();
    }


    @Override
    public void addFlower(Flower flower) {
        flowerRepository.save(flower);
    }

    @Override
    public void changeQuantityFlower(Long flowerId, long quantity) {
        Flower flower = flowerRepository.getOne(flowerId);
        flower.setQuantity(flower.getQuantity()-quantity);
        flowerRepository.saveAndFlush(flower);
    }

    @Override
    public void deleteFlower(Flower flower) {
        flowerRepository.delete(flower);
    }

    @Override
    public void updateFlower(Flower flower) {
        flowerRepository.saveAndFlush(flower);
    }

    @Override
    public Flower getFlowerById(Long idFlower) {
        return flowerRepository.getOne(idFlower);
    }
}
