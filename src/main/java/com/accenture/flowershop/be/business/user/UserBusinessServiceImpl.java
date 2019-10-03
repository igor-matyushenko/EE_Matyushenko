package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.business.order.OrderPositionBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.entity.Order.Order;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.Roles;
import com.accenture.flowershop.fe.enums.StatusOrder;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private Mapper mapper;



    @Override
    @Transactional
    public List<User> getAllUsers() {
        for(User u: userDAO.getUserList()){
            u.getOrderList().size();
            for (Order o: u.getOrderList()){
                o.getBasketOrder().size();
            }
        }

        return userDAO.getUserList();
    }

    @Override
    @Transactional
    public List<User> getAllUsersForLazy() {
        for(User u:userDAO.getUserList()){
            for(Order o : u.getOrderList()) {
                o.setBasketOrder(null);
            }
            u.setOrderList(null);
        }
        return userDAO.getUserList();
    }


    @Override
    @Transactional
    public User userVerification(String login, String password) {
        if (login.isEmpty() || password.isEmpty()) {
            log.warn("Wrong login or password" + login);
            return null;
        }
        User user = findUserByLogin(login);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                log.debug("Login Access" + login);
                if(Roles.USER.equals(user.getRole()))
                    orderBusinessService.getAllOrdersByUserId(user.getId());
                if(Roles.ADMIN.equals(user.getRole()))
                    orderBusinessService.getAllOrders();
                return user;
            }
        }
        return null;
    }

    @Override
    @Transactional
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
    public double getDiscountOfUser(User user) {
        if(user.getDiscount() == 0){
            return 0D;
        }
        return (double) (user.getDiscount()*0.01);
    }

    @Override
    @Transactional
    public boolean payOrder(Long userId, Long orderId) {
        User user = findUserById(userId);
        user.getOrderList().size();
        Order order = orderBusinessService.getOrderById(orderId);
        order.getBasketOrder().size();
        if (user.getBalance().compareTo(order.getTotalPrice()) < 0) {
            return false;
        }
        order.setStatusOrder(StatusOrder.PAID);
        user.setBalance(user.getBalance().subtract(order.getTotalPrice()));
        orderBusinessService.updateOrder(order);
        updateUser(user);
        return true;
    }

    @Override
    public User findUserByLogin(String login) {
        log.debug("FindUserByLogin" + login);
        return userDAO.findUserByLogin(login);
    }

    @Override
    public User findUserById(Long userId) {
        return userDAO.findUserById(userId);
    }

    @Override
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
