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

    @ManyToOne
    @JoinColumn(name = "ID_ORDER")
    private Order order;

    @Column(name = "ID_FLOWER")
    private Long flowerID;

    @Column(name = "ID_USER")
    private Long userID;

    @Column(name = "FLOWER_NAME")
    private String flowerName;

    @Column(name = "QUANTITY")
    private Long quantity;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;



    public OrderPosition() {
    }

    public OrderPosition(Long userID, Long flowerID, String flowerName, Long quantity, BigDecimal totalPrice) {
        this.userID = userID;
        this.flowerID = flowerID;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getFlowerID() {
        return flowerID;
    }

    public void setFlowerID(Long flowerID) {
        this.flowerID = flowerID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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
                ", flowerID=" + flowerID +
                ", userID=" + userID +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
