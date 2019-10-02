package com.accenture.flowershop.be.entity.Order;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.StatusOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS_LIST")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
    @SequenceGenerator(name = "ORDER_SEQ", sequenceName = "ORDER_SEQ", allocationSize = 1)
    @Column(name = "ID_ORDER")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER")
    private List<OrderPosition> basketOrder = new ArrayList<>();

    @Column(name = "LOGIN_USER")
    private String userLogin;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Column(name = "STATUS_ORDER")
    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    @Column(name = "DATE_CREATE")
    private Date dateCreate;

    @Column(name = "DATE_CLOSE")
    private Date dateClose;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public Order() {
    }

    public Order(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
        this.dateCreate = Date.valueOf(LocalDate.now());
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderPosition> getBasketOrder() {
        return basketOrder;
    }

    public void setBasketOrder(List<OrderPosition> basketOrder) {
        this.basketOrder = basketOrder;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userLogin='" + userLogin + '\'' +
                ", totalPrice=" + totalPrice +
                ", statusOrder=" + statusOrder +
                ", dateCreate=" + dateCreate +
                ", dateClose=" + dateClose +
                ", user=" + user +
                '}';
    }
}
