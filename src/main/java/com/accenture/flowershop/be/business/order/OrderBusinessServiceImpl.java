package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.order.OrderDAO;
import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.entity.Order.Basket;
import com.accenture.flowershop.be.entity.Order.Order;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.StatusOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class OrderBusinessServiceImpl implements OrderBusinessService{

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BasketBusinessService basketBusinessService;

    private static final Logger log = LoggerFactory.getLogger(OrderBusinessServiceImpl.class);

    @Override
    public boolean closeOrder(Long orderId) {
        Order order = orderDAO.getOrderById(orderId);
        if(order==null){
            return false;
        }
        order.setDateClose(new Date());
        order.setStatusOrder(StatusOrder.CLOSED);
        orderDAO.updateOrder(order);
        return true;
    }

    @Override
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

    @Override
    public Order newOrderCreate(String userLogin) {
        User user = userDAO.findUserByLogin(userLogin);
        List<Basket> basketList = basketBusinessService.getNewBasketByUserId(user.getId());
        BigDecimal totalPriceOfBasket = new BigDecimal(0);
        for(Basket b : basketList){
            totalPriceOfBasket = totalPriceOfBasket.add(b.getTotalPrice());
        }
        Order order = new Order(StatusOrder.CREATED);
        order.setBasketList(basketList);
        order.setUser(user);
        order.setTotalPrice(totalPriceOfBasket);
        basketBusinessService.setTotalSumFromActualBasket(new BigDecimal(0));
        addOrder(order);
        return order;
    }

    @Override
    public Order getOrderById(Long idOrder) {
        return orderDAO.getOrderById(idOrder);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public List<Order> getOrdersByUserID(Long idUser) {
        return orderDAO.getOrderByUserID(idUser);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }


}
