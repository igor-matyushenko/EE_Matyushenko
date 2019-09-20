package com.accenture.flowershop.be.entity.flower;


import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "Flowers")
public class Flower {
    private Long id;
    private String titleFlower;
    private Long quantity;
    private BigDecimal priceFlower;

    public Flower() {    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLOWERS_SEQ")
    @SequenceGenerator(name = "FLOWERS_SEQ", sequenceName = "FLOWERS_SEQ", allocationSize = 1, initialValue = 2)
    @Column(name = "ID_FLOWER")
    public Long getId() {
        return id;
    }
    @Column(name = "TITLE_FLOWER")
    public String getTitleFlower() {
        return titleFlower;
    }
    @Column(name = "QUANTITY_FLOWER")
    public Long getQuantity() {
        return quantity;
    }
    @Column(name = "PRICE_FLOWER")
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
