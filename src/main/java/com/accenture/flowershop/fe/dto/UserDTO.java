package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.fe.enums.Roles;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private Long id;
    private String login;
    private String password;
    private Roles role ;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;
    private BigDecimal balance ;
    private Integer discount ;
    private List<OrderDTO> orderList = new ArrayList<>();

    public UserDTO() {

    }
    public UserDTO(String login,String password) {
        this.login = login;
        this.password = password;
        this.setDiscount(3);
        this.setRole(Roles.USER);
        this.setBalance(new BigDecimal(2000));
        this.orderList = new ArrayList<>();
    }

    public  void addOrder(OrderDTO order){
        orderList.add(order);
        order.setUser(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", discount=" + discount +
                '}';
    }
}
