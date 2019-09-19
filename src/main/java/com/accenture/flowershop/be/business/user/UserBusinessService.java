package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.user.UserDTO;

public interface UserBusinessService {


    User userVerification(String login, String password);
    User userRegistration(User user);
    Boolean checkLogin(String login);

    User findUserByLogin(String login);

}
