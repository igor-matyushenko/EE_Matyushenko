package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.User;

import java.util.List;

public interface UserBusinessService {


    List<User> getAllUsers();


    User userVerification(String login, String password);

    User userRegistration(User user);

    Boolean checkLogin(String login);

    double getDiscountOfUser(User user);

    User findUserByLogin(String login);

    User findUserById(Long userId);

    void updateUser(User user);

    boolean payOrder(Long userId, Long orderId);
}
