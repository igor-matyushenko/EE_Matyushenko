//package com.accenture.flowershop.be.entity.order;
//
//import com.accenture.flowershop.be.entity.flower.Product;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "ORDER_CONTAINS")
//public class OrderContains {
//
//    @Id
//    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "ID_PRODUCT")
//    private long idProduct;
//
//    @Column(name = "ID_ORDER_CREATED")
//    private long idOrderCreated;
//
//    @Column(name = "VOLUME")
//    private int volume;
//
//    @Column(name = "SUM")
//    private double sum;
//
//    public OrderContains(){}
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public long getIdProduct() {
//        return idProduct;
//    }
//
//    public void setId_product(long idProduct) {
//        this.idProduct = idProduct;
//    }
//
//    public long getIdOrderCreated() {
//        return idOrderCreated;
//    }
//
//    public void setIdOrderCreated(long idOrderCreated) {
//        this.idOrderCreated = idOrderCreated;
//    }
//
//    public int getVolume() {
//        return volume;
//    }
//
//    public void setVolume(int volume) {
//        this.volume = volume;
//    }
//
//    public double getSum() {
//        return sum;
//    }
//
//    public void setSum(double sum) {
//        this.sum = sum;
//    }
//
//    @Override
//    public String toString() {
//        return "Order{" +
//                "id=" + id +
//                ", id_product=" + idProduct +
//                ", id_order_created=" + idOrderCreated +
//                ", volume=" + volume +
//                ", sum=" + sum +
//                '}';
//    }
//}
