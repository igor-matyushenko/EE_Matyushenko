package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.math.BigDecimal;
import java.util.List;

public class OrderPositionDTO {

    private Long idOrderPos;
    private List<Flower> flower;
    private UserDTO user;
    private Long quantity;
    private BigDecimal sumForFlowers;
    private OrderDTO order;

    public OrderPositionDTO() {   }

    public Long getIdOrderPos() {
        return idOrderPos;
    }

    public void setIdOrderPos(Long idOrderPos) {
        this.idOrderPos = idOrderPos;
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

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderPosDTO{" +
                "idOrderPos=" + idOrderPos +
                ", flower=" + flower +
                ", user=" + user.getLogin() +
                ", quantity=" + quantity +
                ", sumForFlowers=" + sumForFlowers +
                ", order=" + order.getId() +
                '}';
    }
}
