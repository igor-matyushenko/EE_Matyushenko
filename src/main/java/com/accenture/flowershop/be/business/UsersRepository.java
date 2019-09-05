package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.user.User;

import java.util.List;

public interface UsersRepository {
    List<User> finAll();
    void save(User user);
    boolean isExist(User user);
    boolean createNewUser(String login, String password);


}
