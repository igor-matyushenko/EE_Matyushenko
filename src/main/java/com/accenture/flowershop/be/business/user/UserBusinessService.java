package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.UserLazyDTO;

import java.util.List;

public interface UserBusinessService {


    List<User> getAllUsers();

    List<UserLazyDTO> getAllUsersForLazy();


    User userVerification(String login, String password);

    User userRegistration(User user);

    Boolean checkLogin(String login);

    double getDiscountOfUser(User user);

    User findUserByLogin(String login);

    User findUserById(Long userId);

    void updateUser(User user);

    void updateUser(UserLazyDTO user);

    boolean payOrder(Long userId, Long orderId);
}
