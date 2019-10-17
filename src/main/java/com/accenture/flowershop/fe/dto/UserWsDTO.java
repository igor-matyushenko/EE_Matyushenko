package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.fe.enums.Roles;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
public class UserWsDTO {

    private Long id;

    private BigDecimal balance ;
    public UserWsDTO() {

    }

    public UserWsDTO(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserWsDTO{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
