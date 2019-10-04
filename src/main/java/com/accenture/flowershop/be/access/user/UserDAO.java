package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;

import java.util.List;

public interface UserDAO {

    void saveUser(User user);

    void updateUser(User user);

    User findUserByLogin(String login);

    List<User> getUserList();

    List<User> getUserListWhithLazy();

    User findUserById(Long idUser);

}
