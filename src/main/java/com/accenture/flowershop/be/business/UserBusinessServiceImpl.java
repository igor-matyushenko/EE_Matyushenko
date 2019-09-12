package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.DAO.user.UserDAO;
import com.accenture.flowershop.be.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private UserDAO userDAO;

    private static final Logger LOG = 	LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    @Override
    public User userVerification(String login, String password) {
        if(login.isEmpty() || password.isEmpty()) {
            LOG.debug("Is Empty login or password");
            return null;
        }
        User user = findUserByLogin(login);
        if (user.getPassword().equals(password)) {
            LOG.debug("User Access");
            return user;
        }
        return null;
    }

    @Override
    public User userRegistration(User user) {
        return null;
    }

    @Override
    public Boolean checkLogin(String login) {
        return null;
    }

    @Override
    public User updateBalance(String login, BigDecimal balance) {
        return null;
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User findUserByLogin(String login) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public void updateDiscount(Long idUser, Integer newDiscount) {

    }

    @Override
    public void pay(Long idUser, BigDecimal priceOrder) {

    }
}
