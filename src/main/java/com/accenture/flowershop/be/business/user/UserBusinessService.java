package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.UserListDTO;
import com.accenture.flowershop.fe.ws.jms.DiscountRequestObject;

import java.math.BigDecimal;
import java.util.List;

public interface UserBusinessService {

    void save(User user);

    User getUser(Long userId);

    List<User> getAllUsers();

    void updateDiscountOfUser(long userId, int userDiscount);

    User userVerification(String login, String password);

    User userRegistration(User user);

    Boolean checkLogin(String login);

    double getDiscountOfUser(User user);

    User findUserByLogin(String login);

    User findUserById(Long userId);

    boolean updateUser(User user);

    boolean payOrder(Long userId, Long orderId);

    public User pay(User user, BigDecimal price);
}
