package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.UserListDTO;

import java.util.List;

public interface UserBusinessService {

    User getUser(Long userId);

    List<User> getAllUsers();

    void updateUserFromJsm(User user);

    User userVerification(String login, String password);

    User userRegistration(User user);

    Boolean checkLogin(String login);

    double getDiscountOfUser(User user);

    User findUserByLogin(String login);

    User findUserById(Long userId);

    boolean updateUser(User user);

    boolean payOrder(Long userId, Long orderId);
}
