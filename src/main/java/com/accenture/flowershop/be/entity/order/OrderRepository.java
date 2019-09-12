package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orderRep")
@Table(name = "ORDER_REPOSITORY")
public class OrderRepository implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_REPOSITORY_SEQ")
    @SequenceGenerator(name = "ORDER_REPOSITORY_SEQ", sequenceName = "ORDER_REPOSITORY_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USERS")
    private User user;

    @OneToMany(mappedBy = "orderRep",
            orphanRemoval = true, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private List<OrderPosition> orderPositions = new ArrayList<>();

    @Column(name = "ALL_SUM")
    private BigDecimal allSum;

    @Column(name = "STATUS")
    private Status status = Status.CREATED;

    @Column(name = "DATA_CREATED")
    private LocalDate dataCreated;

    @Column(name = "DATA_CLOSED")
    private LocalDate dataClosed;

    public OrderRepository(){

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

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public BigDecimal getAllSum() {
        return allSum;
    }

    public void setAllSum(BigDecimal allSum) {
        this.allSum = allSum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDataCreated() {
        return dataCreated;
    }

    public void setDataCreated(LocalDate dataCreated) {
        this.dataCreated = dataCreated;
    }

    public LocalDate getDataClosed() {
        return dataClosed;
    }

    public void setDataClosed(LocalDate dataClosed) {
        this.dataClosed = dataClosed;
    }
}
