package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.flower.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "ORDER_POSITION")
public class OrderPosition implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_POSITION_SEQ")
    @SequenceGenerator(name = "ORDER_POSITION_SEQ", sequenceName = "ORDER_POSITION_SEQ", allocationSize = 1)
    private Long id;

    @OneToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PRODUCT")
    private Product flowers;


    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ORDER_REPOSITORY")
    private OrderRepository orders;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "SUM")
    private BigDecimal sum;

    public OrderPosition(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getFlowers() {
        return flowers;
    }

    public void setFlowers(Product flowers) {
        this.flowers = flowers;
    }

    public OrderRepository getOrders() {
        return orders;
    }

    public void setOrders(OrderRepository orders) {
        this.orders = orders;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
