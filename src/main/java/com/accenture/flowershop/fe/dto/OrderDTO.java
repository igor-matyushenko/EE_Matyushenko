package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.be.entity.Order.OrderPosition;
import com.accenture.flowershop.fe.enums.StatusOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class OrderDTO {

    private Long id;
    private Long userId;
    private UserDTO user;
    private BigDecimal totalPrice;
    private StatusOrder statusOrder;
    private Date dateCreate;
    private Date dateClose;
    private List<OrderPosition> basketOrder = new ArrayList<>();;

    public OrderDTO() {

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderPosition> getBasketOrder() {
        return basketOrder;
    }

    public void setBasketOrder(List<OrderPosition> basketOrder) {
        this.basketOrder = basketOrder;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", user=" + user.getLogin() +
                ", totalPrice=" + totalPrice +
                ", status=" + statusOrder +
                ", dateCreate=" + dateCreate +
                ", dateClose=" + dateClose +
                '}';
    }
}
