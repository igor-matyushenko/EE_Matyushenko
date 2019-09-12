package com.accenture.flowershop.be.DAO.user;

import com.accenture.flowershop.be.entity.user.User;

import java.util.List;

public interface UserDAO {


    User findUserByLogin(String login);

    List<User> getUserList();

    User getUserById(Long id);

    void addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);
}
