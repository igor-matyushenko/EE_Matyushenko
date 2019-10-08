package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.UserListDTO;

import java.util.List;

public interface UserBusinessService {


    List<User> getAllUsers();

    List<UserListDTO> getAllUsersForLazy();


    User userVerification(String login, String password);

    User userRegistration(User user);

    Boolean checkLogin(String login);

    double getDiscountOfUser(User user);

    User findUserByLogin(String login);

    User findUserById(Long userId);

    void updateUser(User user);

    boolean payOrder(Long userId, Long orderId);
}
