package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.fe.enums.Roles;

import java.math.BigDecimal;

public class UserWsDTO {

    private String login;
    private BigDecimal balance ;
    public UserWsDTO() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
                "login='" + login + '\'' +
                ", balance=" + balance +
                '}';
    }
}
