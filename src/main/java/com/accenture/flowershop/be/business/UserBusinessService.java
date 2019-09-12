package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.user.User;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface UserBusinessService {

    User userVerification(String login, String password);

    User userRegistration(User user);

    Boolean checkLogin(String login);

    User updateBalance(String login, BigDecimal balance);

    void deleteUser(User user);

    void updateUser(User user);

    User findUserByLogin(String login);

    User getUserById(Long id);

    @Transactional
    void updateDiscount(Long idUser, Integer newDiscount) ;

    void pay(Long idUser, BigDecimal priceOrder) ;

}
