package com.accenture.flowershop.fe.ws.jms;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DiscountRequestObject {
    long id;
    int discount;

    public DiscountRequestObject() {
    }

    public DiscountRequestObject(long id, int discount) {
        this.id = id;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
