package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.be.entity.Order.Order;
import com.accenture.flowershop.be.entity.flower.Flower;

import java.math.BigDecimal;
import java.util.List;

public class BasketDTO {

    private Long idBasket;
    private List<Flower> flower;
    private UserDTO user;
    private Long quantity;
    private BigDecimal sumForFlowers;
    private Order order;

    public BasketDTO() {   }

    public Long getIdBasket() {
        return idBasket;
    }

    public void setIdBasket(Long idBasket) {
        this.idBasket = idBasket;
    }

    public List<Flower> getFlower() {
        return flower;
    }

    public void setFlower(List<Flower> flower) {
        this.flower = flower;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSumForFlowers() {
        return sumForFlowers;
    }

    public void setSumForFlowers(BigDecimal sumForFlowers) {
        this.sumForFlowers = sumForFlowers;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "BasketDTO{" +
                "idBasket=" + idBasket +
                ", flower=" + flower +
                ", user=" + user.getLogin() +
                ", quantity=" + quantity +
                ", sumForFlowers=" + sumForFlowers +
                ", order=" + order +
                '}';
    }
}
