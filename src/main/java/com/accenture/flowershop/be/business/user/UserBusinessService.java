package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.User;

import java.math.BigDecimal;

public interface UserBusinessService {


    User userVerification(String login, String password);
    User userRegistration(User user);
    Boolean checkLogin(String login);
    Boolean payCreatedOrder(String login, Long orderID);
    User findUserByLogin(String login);
    void editUser(String loginUser, BigDecimal balance);


}
