package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.order.OrderDAO;
import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.entity.Order.OrderPosition;
import com.accenture.flowershop.be.entity.Order.Order;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.StatusOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private OrderPositionBusinessService orderPositionBusinessService;

    private static final Logger log = LoggerFactory.getLogger(OrderBusinessServiceImpl.class);

    @Override
    @Transactional
    public boolean closeOrder(Long orderId) {
        Order order = orderDAO.getOrderById(orderId);
        if (order == null) {
            return false;
        }
        order.setDateClose(Date.valueOf(LocalDate.now()));
        order.setStatusOrder(StatusOrder.CLOSED);
        orderDAO.updateOrder(order);
        return true;
    }

    @Override
    @Transactional
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

    @Override
    @Transactional
    public Order newOrderCreate(String userLogin) {
        User user = userDAO.findUserByLogin(userLogin);
        List<OrderPosition> orderPositionList = orderPositionBusinessService.getNewOrderPositionByUserId(user.getId());
        BigDecimal totalPriceOfBasket = new BigDecimal(0);
        for (OrderPosition b : orderPositionList) {
            totalPriceOfBasket = totalPriceOfBasket.add(b.getTotalPrice());
        }
        Order order = new Order(StatusOrder.CREATED);
        order.setOrderPositionList(orderPositionList);
        order.setUserLogin(user.getLogin());
        order.setTotalPrice(totalPriceOfBasket);
        orderPositionBusinessService.setTotalSumFromActualBasket(new BigDecimal(0));
        addOrder(order);
        return order;
    }

    @Override
    @Transactional
    public Order getOrderById(Long idOrder) {
        return orderDAO.getOrderById(idOrder);
    }

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    @Transactional
    public List<Order> getOrdersByUserLogin(String loginUser) {
        return  orderDAO.getOrderByUser(loginUser);
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }


}
