package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.math.BigDecimal;
import java.util.List;

public class OrderPositionDTO {

    private Long idOrderPosition;
    private Long orderId;
    private Long flowerId;
    private Long userId;
    private String flowerName;
    private Long quantity;
    private BigDecimal totalPrice;

    public OrderPositionDTO() {   }

    public Long getIdOrderPosition() {
        return idOrderPosition;
    }

    public void setIdOrderPosition(Long idOrderPosition) {
        this.idOrderPosition = idOrderPosition;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(Long flowerId) {
        this.flowerId = flowerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderPositionDTO{" +
                "idOrderPosition=" + idOrderPosition +
                ", orderId=" + orderId +
                ", flowerId=" + flowerId +
                ", userId=" + userId +
                ", flowerName='" + flowerName + '\'' +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
