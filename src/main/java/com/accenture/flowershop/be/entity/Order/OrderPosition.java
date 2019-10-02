package com.accenture.flowershop.be.entity.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "ORDER_POSITION")
public class OrderPosition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_POSITION_SEQ")
    @SequenceGenerator(name = "ORDER_POSITION_SEQ", sequenceName = "ORDER_POSITION_SEQ", allocationSize = 1)
    @Column(name = "ID_ORDER_POSITION")
    private Long idOrderPosition;

    @Column(name = "ID_ORDER")
    private Long orderId;

    @Column(name = "ID_FLOWER")
    private Long flowerId;

    @Column(name = "ID_USER")
    private Long userId;

    @Column(name = "FLOWER_NAME")
    private String flowerName;

    @Column(name = "QUANTITY")
    private Long quantity;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;



    public OrderPosition() {
    }

    public OrderPosition(Long orderId, Long flowerId, Long userId, String flowerName, Long quantity, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.flowerId = flowerId;
        this.userId = userId;
        this.flowerName = flowerName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

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
        return "OrderPosition{" +
                "idOrderPosition=" + idOrderPosition +
                ", orderId=" + orderId +
                ", flowerid=" + flowerId +
                ", userid=" + userId +
                ", flowerName='" + flowerName + '\'' +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
