package com.accenture.flowershop.be.entity.Order;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.StatusOrder;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "OrderList")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
    @SequenceGenerator(name = "ORDER_SEQ", sequenceName = "ORDER_SEQ", allocationSize = 1)
    @Column(name = "ID_ORDER")
    private Long id;

//    @Column(name = "id_user")
//    private Long userId;

    @Column(name = "total_price")
    private BigDecimal totalPrice;


    @Column(name = "status_order")
    @Enumerated(EnumType.STRING)
    private StatusOrder status;

    @Column(name = "date_create")
    private LocalDate dateCreate;

    @Column(name = "date_close")
    private LocalDate dateClose;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_order")
    private List<Basket> basketList=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Order() {
    }

    public Order(StatusOrder statusOrder){
        this.status = statusOrder;
        setDateCreate(LocalDate.now());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

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

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public List<Basket> getBasketList() {
        return basketList;
    }

    public void setBasketList(List<Basket> basketList) {
        this.basketList = basketList;
    }

    public LocalDate getDateClose() {
        return dateClose;
    }

    public void setDateClose(LocalDate dateClose) {
        this.dateClose = dateClose;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +

                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", dateCreate=" + dateCreate +
                ", dateClose=" + dateClose +
                ", basketList=" + basketList +
                '}';
    }
}
