package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.access.flower.FlowerDAO;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {

    @Autowired
    private FlowerDAO flowerDAO;

    @Override
    public List<Flower> getAllFlowers() {
        return flowerDAO.getAllFlowers();
    }

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
            for (Flower flower : flowerDAO.getAllFlowers()) {
                int minP = flower.getPriceFlower().compareTo(minPrice);
                int maxP = flower.getPriceFlower().compareTo(maxPrice);
                if (maxP <= 0 && minP >= 0) {
                    flowerListBySearch.add(flower);
                }
            }
            return flowerListBySearch;
        }
        return flowerDAO.getAllFlowersBySearch(flowerName.toUpperCase(), minPrice, maxPrice);
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
    public void updateFlower(Flower flower) {
        flowerDAO.updateFlower(flower);
    }

    @Override
    public Flower getFlowerById(Long idFlower) {
        return flowerDAO.getFlowerById(idFlower);
    }
}
