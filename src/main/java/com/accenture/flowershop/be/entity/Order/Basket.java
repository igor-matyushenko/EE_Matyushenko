package com.accenture.flowershop.be.entity.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "BASKET")
public class Basket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BASKET_SEQ")
    @SequenceGenerator(name = "BASKET_SEQ", sequenceName = "BASKET_SEQ", allocationSize = 1)
    @Column(name = "ID_BASKET")
    private Long idBasket;

    @Column(name = "ID_ORDER")
    private Long orderID;

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

//    @ManyToOne
//    @JoinColumn(name = "id_order")
//    private Order order;

    public Basket() {
    }

    public Basket(Long userID, Long flowerID, String flowerName, Long quantity, BigDecimal totalPrice) {
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

    public Long getIdBasket() {
        return idBasket;
    }

    public void setIdBasket(Long idBasket) {
        this.idBasket = idBasket;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
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
        return "Basket{" +
                "idBasket=" + idBasket +
                ", flowerID=" + flowerID +
                ", userID=" + userID +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
