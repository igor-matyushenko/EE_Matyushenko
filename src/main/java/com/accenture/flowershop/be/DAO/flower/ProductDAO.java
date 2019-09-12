package com.accenture.flowershop.be.DAO.flower;

import com.accenture.flowershop.be.entity.flower.Product;
import com.accenture.flowershop.be.entity.order.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDAO {

    Product findFlowerById(Long id);

    List<Product> getFlowerList();

    void addFlower(Product flower);

    void deleteFlower(Product flower);

    void updateFlower(Product flower);

}
