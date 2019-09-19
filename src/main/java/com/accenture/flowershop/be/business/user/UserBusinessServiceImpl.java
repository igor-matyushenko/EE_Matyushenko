package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.user.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    private static final Logger log = 	LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    @Autowired
    private UserDAO userDAO;


    @Override
    public User userVerification(String login, String password) {
        if(login.isEmpty() || password.isEmpty()) {
            log.debug("Wrong login or password");
            return null;
        }
        try {
            User user = findUserByLogin(login);
            if (user.getPassword().equals(password)) {
                log.debug("Login Access");
                return user;
            }
        }catch (NullPointerException e){
            return null;
        }

        return null;
    }

    @Override
    public User userRegistration(User user) {
        if (userDAO.findUserByLogin(user.getLogin()) == null) {
            userDAO.saveUser(user);
            log.debug("Registration successful");
            return user;
        }
        log.debug("Registration invalid");
        return null;
    }

    @Override
    public Boolean checkLogin(String login) {
        log.debug("Check login");
        User user = userDAO.findUserByLogin(login);
        if(user != null) {
            return true;
        }
        return false;
    }

    @Override
    public User findUserByLogin(String login) {
        log.debug("FindUserByLogin");
        return userDAO.findUserByLogin(login);
    }


}
