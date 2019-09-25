package com.accenture.flowershop.be.entity.Order;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
public class Basket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Basket_SEQ")
    @SequenceGenerator(name = "Basket_SEQ", sequenceName = "BASKET_SEQ", allocationSize = 1)
    @Column(name = "id_basket")
    private Long idBasket;

    @Column(name = "id_order")
    private Long orderID;

    @Column(name = "id_flower")
    private Long flowerID;

    @Column(name = "flower_name")
    private String flowerName;

    @Column(name = "id_user")
    private Long userID;

    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "totalPrice")
    private BigDecimal totalPrice;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_order")
//    private Order order;



    public Basket() {   }

    public Basket(Long userID,  Long flowerID, String flowerName, Long quantity, BigDecimal totalPrice) {
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
