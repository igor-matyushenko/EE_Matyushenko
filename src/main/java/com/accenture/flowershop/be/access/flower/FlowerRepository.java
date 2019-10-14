package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {

    @Query(value = "select f from Flower f where " +
            "upper(f.titleFlower) like '%' || :flowerName || '%' " +
            "and f.priceFlower between :minPrice and :maxPrice ")
    List<Flower> getAllFlowersBySearch(
            @Param("flowerName") String flowerName,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice);
}


