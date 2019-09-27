package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.be.entity.Order.Basket;
import com.accenture.flowershop.fe.enums.StatusOrder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO {

    private Long id;
    private UserDTO user;
    private BigDecimal totalPrice;
    private StatusOrder statusOrder;
    private Date dateCreate;
    private Date dateClose;
    private List<Basket> basketList;

    public OrderDTO() {
        basketList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateClose() {
        return dateClose;
    }

    public void setDateClose(Date dateClose) {
        this.dateClose = dateClose;
    }

    public List<Basket> getBasketList() {
        return basketList;
    }

    public void setBasketList(List<Basket> basketList) {
        this.basketList = basketList;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", user=" + user.getPassword() +
                ", totalPrice=" + totalPrice +
                ", status=" + statusOrder +
                ", dateCreate=" + dateCreate +
                ", dateClose=" + dateClose +
                ", basketList=" + basketList +
                '}';
    }
}
