package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.order.OrderDAO;
import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.BasketBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.entity.Order.Basket;
import com.accenture.flowershop.be.entity.Order.Order;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.StatusOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    private static final Logger log = 	LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private BasketBusinessService basketBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;


    @Override
    public BigDecimal userSumDiscount(BigDecimal priceFlower, Integer userDiscount, Long quantityToBasket) {
        BigDecimal discount =  new BigDecimal(userDiscount*0.01);
        BigDecimal sumFlower = priceFlower.multiply(new BigDecimal(quantityToBasket));
        BigDecimal discountForUser = sumFlower.multiply(discount);
        BigDecimal sumForFlowerWithUserCount = sumFlower.subtract(discountForUser);
        return sumForFlowerWithUserCount.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public User userVerification(String login, String password) {
        if(login.isEmpty() || password.isEmpty()) {
            log.debug("Wrong login or password");
            return null;
        }
            User user = findUserByLogin(login);
        if(user!=null){
            if (user.getPassword().equals(password)) {
                log.debug("Login Access");
                return user;
            }
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
    public Boolean payCreatedOrder(String login, Long orderID) {
        if(orderID==null) return false;
        User user = userDAO.findUserByLogin(login);
        Order order = orderBusinessService.getOrderById(orderID);
        return payOfOrder(user, order);
    }
    private boolean payOfOrder(User user, Order order){
        if(user.getBalance().compareTo(order.getTotalPrice())<0){
            return false;
        }
        editUser(user.getLogin(),user.getBalance().subtract(order.getTotalPrice()));
        order.setStatus(StatusOrder.PAID);
        orderBusinessService.editOrder(order);
        basketBusinessService.editBasket(basketBusinessService.getBasketByUserId(user.getId()));
        return true;
    }

    @Override
    public User findUserByLogin(String login) {
        log.debug("FindUserByLogin");
        return userDAO.findUserByLogin(login);
    }

    @Override
    public void editUser(String loginUser, BigDecimal balance) {
        User user = findUserByLogin(loginUser);
        user.setBalance(balance);
        userDAO.editUser(user);
    }


}
