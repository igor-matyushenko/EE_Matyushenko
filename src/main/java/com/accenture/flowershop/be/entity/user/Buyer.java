package com.accenture.flowershop.be.entity.user;

import javax.persistence.*;

@Entity
@Table(name = "BUYER")
public class Buyer {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "SECONDNAME")
    private String secondName;

    @Column(name = "PHONENUMBER")
    private int phoneNumber;

    @Column(name = "ID_ADDRESS")
    private long id_address;

    @Column(name = "BALANCE")
    private double balance;

    @Column(name = "DISCOUNT")
    private int discount;

    public Buyer() { }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getId_address() {
        return id_address;
    }

    public void setId_address(long id_address) {
        this.id_address = id_address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", id_address=" + id_address +
                ", balance=" + balance +
                ", discount=" + discount +
                '}';
    }
}
