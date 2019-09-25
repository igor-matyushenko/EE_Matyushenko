package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.be.entity.Order.Basket;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.StatusOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

    private Long id;
    private User user;
    private List<Basket> orderBasket;
    private BigDecimal totalPrice;
    private StatusOrder status;
    private LocalDateTime dateCreate;
    private LocalDateTime dateClose;

    public OrderDTO() {
    }

    public OrderDTO(StatusOrder statusOrder){
        this.status = statusOrder;
        setDateCreate(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Basket> getOrderBasket() {
        return orderBasket;
    }

    public void setOrderBasket(List<Basket> orderBasket) {
        this.orderBasket = orderBasket;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public LocalDateTime getDateClose() {
        return dateClose;
    }

    public void setDateClose(LocalDateTime dateClose) {
        this.dateClose = dateClose;
    }
}
