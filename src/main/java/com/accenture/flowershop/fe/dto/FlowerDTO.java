package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class FlowerDTO {
    private Long id;
    private String titleFlower;
    private Long quantity;
    private BigDecimal priceFlower;

    public FlowerDTO() {    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleFlower() {
        return titleFlower;
    }

    public void setTitleFlower(String titleFlower) {
        this.titleFlower = titleFlower;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceFlower() {
        return priceFlower;
    }

    public void setPriceFlower(BigDecimal priceFlower) {
        this.priceFlower = priceFlower;
    }

    @Override
    public String toString() {
        return "FlowerDTO{" +
                "id=" + id +
                ", titleFlower='" + titleFlower + '\'' +
                ", quantity=" + quantity +
                ", priceFlower=" + priceFlower +
                '}';
    }
}
