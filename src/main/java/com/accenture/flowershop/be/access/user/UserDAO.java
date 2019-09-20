package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;

public interface UserDAO {

    void saveUser(User user);
    void editUser(User user);
    User findUserByLogin(String login);
    User findUserById(Long idUser);


}
