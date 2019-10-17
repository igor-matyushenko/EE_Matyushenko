package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FlowerRepository extends JpaRepository<Flower,Long>, QueryDslPredicateExecutor<Flower> {

//    @Query(value = "select f from Flower f where " +
//            "upper(f.titleFlower) like '%' || :flowerName || '%' " +
//            "and f.priceFlower between :minPrice and :maxPrice ")
//    List<Flower> getAllFlowersBySearch(
//            @Param("flowerName") String flowerName,
//            @Param("minPrice") BigDecimal minPrice,
//            @Param("maxPrice") BigDecimal maxPrice);

    List<Flower> findByTitleFlowerContainingAndPriceFlowerBetween(String titleFlower, BigDecimal minPrice, BigDecimal maxPrice);
    List<Flower> findByPriceFlowerBetween(BigDecimal minPrice, BigDecimal maxPrice);
    List<Flower> findByTitleFlowerContaining(String titleFlower);

}


