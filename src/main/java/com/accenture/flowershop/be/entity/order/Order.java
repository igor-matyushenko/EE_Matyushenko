package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.fe.enums.order.Status;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ORDER_CREATED")
public class Order {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ID_BUYER")
    private long idBuyer;

    @Column(name = "ALL_SUM")
    private double allSum;

    @Column(name = "STATUS")
    private Status status;

    @Column(name = "DATACREATED")
    private LocalDate dataCreated;

    @Column(name = "DATACLOSED")
    private LocalDate dataClosed;

    public Order(){

    }


}
