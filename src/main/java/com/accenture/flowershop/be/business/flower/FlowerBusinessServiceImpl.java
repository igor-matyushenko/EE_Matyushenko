package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.entity.flower.Flower;

import com.accenture.flowershop.be.access.flower.FlowerRepository;
import com.querydsl.core.BooleanBuilder;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accenture.flowershop.be.entity.flower.QFlower;

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
            return flowerRepository.findByPriceFlowerBetween(minPrice, maxPrice);
        }
        return flowerRepository.findByTitleFlowerContainingAndPriceFlowerBetween(flowerName, minPrice, maxPrice);
    }

    @Override
    public List<Flower> getAllFlowersBySearchQueryDsl(String flowerName, BigDecimal minPrice, BigDecimal maxPrice) {
        BooleanBuilder where = new BooleanBuilder();
        if (flowerName != null) {
            where.and(QFlower.flower.titleFlower.containsIgnoreCase(flowerName));
        }
        if (maxPrice != null) {
            where.and(QFlower.flower.priceFlower.loe(maxPrice));
        }
        if (minPrice != null) {
            where.and(QFlower.flower.priceFlower.goe(minPrice));
        }
        return Lists.newArrayList(flowerRepository.findAll(where.getValue()));
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
        flower.setQuantity(flower.getQuantity() - quantity);
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
