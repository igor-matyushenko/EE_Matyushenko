package com.accenture.flowershop.fe.dto.user;

import com.accenture.flowershop.fe.enums.Roles;

import java.math.BigDecimal;

public class UserDTO {
    private Long idUser;
    private String login;
    private String password;
    private Roles role;
    private String name;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private BigDecimal balance ;
    private Integer discount;

    public UserDTO() {
        setRole(Roles.USER);
        setBalance(new BigDecimal(2000));
        setDiscount(3);
    }


    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public com.accenture.flowershop.fe.enums.Roles getRole() {return role;}

    public void setRole(Roles role) {this.role = role;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
