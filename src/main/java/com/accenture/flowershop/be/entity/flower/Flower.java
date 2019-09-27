package com.accenture.flowershop.be.entity.flower;


import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "FLOWERS")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLOWERS_SEQ")
    @SequenceGenerator(name = "FLOWERS_SEQ", sequenceName = "FLOWERS_SEQ", allocationSize = 1)
    @Column(name = "ID_FLOWER")
    private Long id;
    @Column(name = "TITLE_FLOWER")
    private String titleFlower;
    @Column(name = "QUANTITY_FLOWER")
    private Long quantity;
    @Column(name = "PRICE_FLOWER")
    private BigDecimal priceFlower;

    public Flower() {
    }

    public Long getId() {
        return id;
    }

    public String getTitleFlower() {
        return titleFlower;
    }

    public Long getQuantity() {
        return quantity;
    }

    public BigDecimal getPriceFlower() {
        return priceFlower;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitleFlower(String titleFlower) {
        this.titleFlower = titleFlower;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setPriceFlower(BigDecimal priceFlower) {
        this.priceFlower = priceFlower;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id=" + id +
                ", titleFlower='" + titleFlower + '\'' +
                ", quantity=" + quantity +
                ", priceFlower=" + priceFlower +
                '}';
    }
}
