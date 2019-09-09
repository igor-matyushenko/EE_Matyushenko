//package com.accenture.flowershop.be.entity.flower;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "PRODUCT")
//public class Product {
//    @Id
//    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "TITLE")
//    private String title;
//
//    @Column(name = "PRICE")
//    private double price;
//
//    @Column(name = "VOLUME")
//    private int volume;
//
//    public Product(){}
//// get & set//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
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
//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", price=" + price +
//                ", volume=" + volume +
//                '}';
//    }
//}
