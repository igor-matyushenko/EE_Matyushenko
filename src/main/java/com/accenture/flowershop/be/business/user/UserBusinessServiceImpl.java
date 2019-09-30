package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.business.order.OrderPositionBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.entity.Order.Order;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.StatusOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    private static final Logger log = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private OrderPositionBusinessService orderPositionBusinessService;

    @Override
    @Transactional
    public List<User> getAllUser() {
        return userDAO.getUserList();
    }

    @Override
    public BigDecimal userSumDiscount(BigDecimal priceFlower, int discountUser, Long quantityToBasket) {
        BigDecimal discount = new BigDecimal(discountUser * 0.01);
        BigDecimal sumFlower = priceFlower.multiply(new BigDecimal(quantityToBasket));
        BigDecimal discountForUser = sumFlower.multiply(discount);
        BigDecimal sumForFlowerWithUserCount = sumFlower.subtract(discountForUser);
        return sumForFlowerWithUserCount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public User userVerification(String login, String password) {
        if (login.isEmpty() || password.isEmpty()) {
            log.warn("Wrong login or password" + login);
            return null;
        }
        User user = findUserByLogin(login);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                log.debug("Login Access" + login);
                return user;
            }
        }
        return null;
    }

    @Override
    public User userRegistration(User user) {
        if (userDAO.findUserByLogin(user.getLogin()) == null) {
            userDAO.saveUser(user);
            log.debug("Registration successful:" + user.getLogin());
            return user;
        }
        log.warn("Registration invalid: " + user.getLogin());
        return null;
    }

    @Override
    public Boolean checkLogin(String login) {
        log.debug("Check login: " + login);
        User user = userDAO.findUserByLogin(login);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean payCreatedOrder(String login, Long orderID) {
        User user = userDAO.findUserByLogin(login);
        Order order = orderBusinessService.getOrderById(orderID);
        return payOfOrder(user, order);
    }

    private boolean payOfOrder(User user, Order order) {
        if (user.getBalance().compareTo(order.getTotalPrice()) < 0) {
            return false;
        }
        user = findUserByLogin(user.getLogin());
        user.setBalance(user.getBalance().subtract(order.getTotalPrice()));
        updateUser(user);
        order.setStatusOrder(StatusOrder.PAID);
        orderBusinessService.updateOrder(order);
        orderPositionBusinessService.updateOrderPositionList(orderPositionBusinessService.getOrderPositionByUserId(user.getId()));
        return true;
    }

    @Override
    @Transactional
    public User findUserByLogin(String login) {
        log.debug("FindUserByLogin" + login);
        return userDAO.findUserByLogin(login);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        User updateUser = findUserByLogin(user.getLogin());
        updateUser.setLogin(user.getLogin());
        updateUser.setPassword(user.getPassword());
        updateUser.setBalance(user.getBalance());
        updateUser.setDiscount(user.getDiscount());
        updateUser.setAddress(user.getAddress());
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        userDAO.updateUser(updateUser);
    }

}
