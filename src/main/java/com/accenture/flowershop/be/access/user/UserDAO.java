package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;

public interface UserDAO {

    User saveUser(User user);
    User updateUser(User user);
    User findUserByLogin(String login);
    User findUserById(Long idUser);


}
